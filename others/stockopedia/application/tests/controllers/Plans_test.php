<?php

class Plans_test extends TestCase {
    
    public function test_find() {
    
        $required_keys = ["id", "code", "name"];
        
        $output = $this->request("GET", "/plans");
        $output = json_decode($output, true);
        $this->assertResponseCode(200);
        $this->assertGreaterThan(0, count($output));
        
        foreach($required_keys as $key) {
            $this->assertArrayHasKey($key, $output[0]);
        }
    }
    
    public function test_findOne() {
    
        $required_keys = ["id", "code", "name", "monthly_cost", "annual_cost", "active"];
        $id = 1;
        
        $output = $this->request("GET", "/plans/" . $id);
        $output = json_decode($output, true);
        $this->assertResponseCode(200);
    
        foreach($required_keys as $key) {
            $this->assertArrayHasKey($key, $output);
        }
        $this->assertEquals($id, $output["id"]);
    }
}