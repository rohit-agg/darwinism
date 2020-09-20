<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Subscriptions extends CI_Controller {
    
    public function index_post() {
        
        $this->load->model("subscription_model");
        
        $data = $this->input->input_stream();
        $data = json_decode($data);
        
        $user_details = array(
            "email_id" => $data["email_id"]
        );
        
        $plan_details = array();
        foreach ($data["plans"] as $plan_id => $frequency) {
            $plan_details = array(
                "plan_id" => $plan_id,
                "frequency" => $frequency
            );
        }
        
        $this->subscription_model->create_subscriptions($user_details, $plan_details);
    }
    
    public function cost_post() {
        
        $this->load->model("plan_model");
        
        $data = $this->input->input_stream();
        $data = json_decode($data);
        
        $cost = 0;
        
        $fields = array("id", "monthly_cost", "annual_cost");
        $filters = array("id" => array_keys($data["plans"]));
        $plan_list = $this->plan_model->filter_plan_list($filters, $fields);
        
        foreach ($plan_list as $plan) {
            
            if (!array_key_exists($plan["id"], $data['plans'])) {
                continue;
            }
            
            $cost += strcasecmp($data["plans"][$plan["id"]], "monthly") ? $plan["monthly_cost"] : $plan["annual_cost"];
        }
        
        return json_encode(array("annual_cost" => $cost));
    }
}