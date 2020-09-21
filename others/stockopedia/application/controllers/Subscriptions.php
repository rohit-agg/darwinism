<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Subscriptions extends CI_Controller {
    
    public function create() {
        
        $this->load->model("subscription_model");
        
        $data = $this->input->raw_input_stream;
        $data = $this->security->xss_clean($data);
        $data = json_decode($data, true);
        
        $user_details = array(
            "email_id" => $data["email_id"]
        );
        
        $plan_details = array();
        foreach ($data["plans"] as $plan_id => $frequency) {
            $plan_details[] = array(
                "plan_id" => $plan_id,
                "frequency" => $frequency
            );
        }
        
        $this->subscription_model->create_subscriptions($user_details, $plan_details);
        $this->output->set_status_header(201);
    }
    
    public function costs() {
        
        $this->load->model("plan_model");
        
        $data = $this->input->raw_input_stream;
        log_message("error", $data);
        $data = $this->security->xss_clean($data);
        $data = json_decode($data, true);
        
        log_message("error", print_r($data,1));
        
        $monthly_cost = 0;
        $annual_cost = 0;
        
        $fields = array("id", "monthly_cost", "annual_cost");
        $filters = array("id" => array_keys($data["plans"]));
        $plan_list = $this->plan_model->filter_plan_list($filters, $fields);
        
        foreach ($plan_list as $plan) {
            
            if (!array_key_exists($plan["id"], $data['plans'])) {
                continue;
            }
            
            if (strcasecmp($data["plans"][$plan["id"]], "monthly") === 0) {
                $monthly_cost += $plan["monthly_cost"];
            } else {
                $annual_cost += $plan["annual_cost"];
            }
        }
        
        $response = array(
            "monthly_cost" => $monthly_cost,
            "annual_cost" => $annual_cost
        );
        
        $this->output
            ->set_content_type("application/json")
            ->set_output(json_encode($response));
    }
}