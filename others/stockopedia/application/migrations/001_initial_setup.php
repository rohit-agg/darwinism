<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Migration_Initial_setup extends CI_Migration {
    
    public function up() {
    
        $this->dbforge->add_field("id");
        $this->dbforge->add_field(array(
            "code" => array(
                "type" => "CHAR",
                "constraint" => 4,
                "null" => false,
                "unique" => true,
            ),
            "name" => array(
                "type" => "VARCHAR",
                "constraint" => 64,
                "null" => false
            ),
            "monthly_cost" => array(
                "type" => "DECIMAL",
                "constraint" => array(5, 2),
                "unsigned" => true,
                "null" => false
            ),
            "annual_cost" => array(
                "type" => "DECIMAL",
                "constraint" => array(5, 2),
                "unsigned" => true,
                "null" => false
            ),
            "active" => array(
                "type" => "ENUM",
                "constraint" => array("yes", "no"),
                "null" => false
            )
        ));
        $this->dbforge->create_table("plans");
    
        $this->dbforge->add_field("id");
        $this->dbforge->add_field(array(
            "email_id" => array(
                "type" => "VARCHAR",
                "constraint" => 64,
                "null" => false
            ),
            "plan_id" => array(
                "type" => "INT",
                "constraint" => 9,
                "unsigned" => true,
                "null" => false
            ),
            "frequency" => array(
                "type" => "ENUM",
                "constraint" => array("monthly", "annually"),
                "null" => false
            ),
            "active" => array(
                "type" => "ENUM",
                "constraint" => array("yes", "no"),
                "null" => false
            )
        ));
        $this->dbforge->add_key(array("email_id", "plan_id"));
        $this->dbforge->create_table("subscriptions");
    }
}