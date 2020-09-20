<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Plan_model extends CI_Model {

    public function get_plan_details($plan_id) {
        
        $this->db->where("id", $plan_id);
        $this->db->where("active", "yes");
        $this->db->from("plans");
        $query = $this->db->get();
        return $query->row();
    }
    
    public function filter_plan_list(array $filters, $fields = array()) {
        
        if (empty($fields)) {
            $fields = array("id", "code", "name");
        }
        $this->db->select($fields);
        
        foreach ($filters as $key => $value) {
            if (is_array($value)) {
                $this->db->where_in($key, $value);
            } else {
                $this->db->where($key, $value);
            }
        }
        
        $this->db->from("plans");
        $query = $this->db->get();
        return $query->result();
    }
}