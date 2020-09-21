<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Plans extends CI_Controller {
    
    public function find() {
    
        $this->load->model("plan_model");
        $plans = $this->plan_model->filter_plan_list(array("active" => "yes"));
        $this->output
            ->set_content_type("application/json")
            ->set_output(json_encode($plans));
    }
    
    public function findOne($id) {
    
        $this->load->model("plan_model");
        $plan = $this->plan_model->get_plan_details($id);
        $this->output
            ->set_content_type("application/json")
            ->set_output(json_encode($plan));
    }
}