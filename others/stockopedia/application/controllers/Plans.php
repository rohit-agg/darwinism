<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Plans extends CI_Controller {
    
    public function index_get($plan_id = null) {
    
        $this->load->model("plan_model");
        
        if (empty($plan_id)) {
            $plans = $this->plan_model->filter_plan_list(array("active" => "yes"));
            return json_encode($plans);
        } else {
            $plan = $this->plan_model->get_plan_details($plan_id);
            return json_encode($plan);
        }
    }
}