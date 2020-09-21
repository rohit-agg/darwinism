<?php

class Subscriptions_test extends TestCase {
    
    public function test_costs() {
    
        $input = array(
            "plans" => array(
                "1" => "monthly",
                "3" => "annually"
            )
        );
        $output = $this->request("POST", "/subscriptions/costs", json_encode($input));
        $output = json_decode($output, true);
        
        $this->assertResponseCode(200);
        $this->assertArrayHasKey("monthly_cost", $output);
        $this->assertArrayHasKey("annual_cost", $output);
        $this->assertEquals($output["monthly_cost"], 10);
        $this->assertEquals($output["annual_cost"], 75);
    }
    
    public function test_create() {
    
        $input = array(
            "email_id" => "rohit.aggarwal@stockopedia.com",
            "plans" => array(
                "1" => "monthly",
                "3" => "annually"
            )
        );
        $this->request("POST", "/subscriptions", json_encode($input));
    
        $this->assertResponseCode(201);
    }
}