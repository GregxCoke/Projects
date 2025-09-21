<?php

class User {

    private $name, $email_address, $cash_balance, $id;
    
    public function __construct($name, $email_address, $cash_balance, $id = 0) {
        
        $this->set_name($name);
        $this->set_email_address($email_address);
        $this->set_cash_balance($cash_balance);
        $this->set_id($id);
        
    }
    
    public function set_name ($name) {
        $this->name = $name;     
    }
    
    public function get_name () {
        return $this->name;
    }
    
    public function set_email_address($email_address): void {
        $this->email_address = $email_address;
    }
    
    public function get_email_address() {
        return $this->email_address;
    }
    
    public function set_id($id): void {
        $this->id = $id;
    }

    public function get_id() {
        return $this->id;
    }

    public function set_cash_balance ($cash_balance) {
        $this->cash_balance = $cash_balance;
    }
    
    public function get_cash_balance () {
        return $this->cash_balance;
    }
}

function list_users() {
    global $database;

    $query = 'SELECT name, email_address, cash_balance, id FROM users';

    // prepare the query please
    $statement = $database->prepare($query);

    // run the query please
    $statement->execute();

    // this might be risky if you have HUGE amounts of data
    $users = $statement->fetchAll();

    $statement->closeCursor();
    
    $users_array = array();
    
    foreach ($users as $user) {
        $users_array[] = new User($user['name'], $user['email_address'],
                $user['cash_balance'], $user['id']);
    }
    
    return $users_array;
    //return $users;
}

function insert_user($user) {
    global $database;

    $query = "INSERT INTO users (name, email_address, cash_balance) "
            . "VALUES (:name, :email_address, :cash_balance)";

    // value binding in PDO protects against sql injection
    $statement = $database->prepare($query);
    $statement->bindValue(":name", $user->get_name());
    $statement->bindValue(":email_address", $user->get_email_address());
    $statement->bindValue(":cash_balance", $user->get_cash_balance());

    $statement->execute();

    $statement->closeCursor();
}

function update_user($user) {
    global $database;

    $query = "update users set name = :name, cash_balance = :cash_balance "
            . " where email_address = :email_address";

    // value binding in PDO protects against sql injection
    $statement = $database->prepare($query);
    $statement->bindValue(":name", $user->get_name());
    $statement->bindValue(":email_address", $user->get_email_address());
    $statement->bindValue(":cash_balance", $user->get_cash_balance());

    $statement->execute();

    $statement->closeCursor();
}

function delete_user($user) {
    global $database;

    $query = "delete from users "
            . " where email_address = :email_address";

    // value binding in PDO protects against sql injection
    $statement = $database->prepare($query);
    $statement->bindValue(":email_address", $user->get_email_address());

    $statement->execute();

    $statement->closeCursor();
}
