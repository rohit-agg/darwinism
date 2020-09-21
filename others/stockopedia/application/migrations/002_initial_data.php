<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Migration_Initial_data extends CI_Migration {
    
    public function up() {
    
        $plans = array(
            array(
                "code" => "gb",
                "name" => "UK",
                "monthly_cost" => 10,
                "annual_cost" => 50,
                "active" => "yes"
            ),
            array(
                "code" => "fr",
                "name" => "France",
                "monthly_cost" => 10,
                "annual_cost" => 60,
                "active" => "yes"
            ),
            array(
                "code" => "de",
                "name" => "Germany",
                "monthly_cost" => 15,
                "annual_cost" => 75,
                "active" => "yes"
            ),
            array(
                "code" => "us",
                "name" => "USA",
                "monthly_cost" => 25,
                "annual_cost" => 150,
                "active" => "yes"
            ),
            array(
                "code" => "jp",
                "name" => "Japan",
                "monthly_cost" => 15,
                "annual_cost" => 65,
                "active" => "yes"
            )
        );
        $this->db->insert_batch("plans", $plans);
    }
}