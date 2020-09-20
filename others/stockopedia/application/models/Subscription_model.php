<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Subscription_model extends CI_Model {
    
    public function create_subscriptions($user, $plans) {
    
        $this->db->trans_start();
        foreach ($plans as $plan) {
            
            $subscription = array(
                "email_id" => $user["email_id"],
                "plan_id" => $plan["plan_id"],
                "frequency" => $plan["frequency"],
                "active" => "yes"
            );
            $this->db->insert("subscriptions", $subscription);
        }
        $this->db->trans_complete();
    }
}