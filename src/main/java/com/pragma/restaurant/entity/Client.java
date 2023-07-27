package com.pragma.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

public class Client {
    @Entity(name = "user_entity")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;
        @Column(name = "name", nullable = false)
        private String name;
        @Column(name = "order_active", nullable = false)
        private boolean orderActive = false;
        @OneToMany(mappedBy = "userOrder")
        @JsonManagedReference
        @JsonIgnore
        private List<Order> order;

        public UserEntity() {
        }

        public UserEntity(Integer id, String name, boolean orderActive, List<Order> order) {
            this.id = id;
            this.name = name;
            this.orderActive = orderActive;
            this.order = order;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isOrderActive() {
            return orderActive;
        }

        public void setOrderActive(boolean orderActive) {
            this.orderActive = orderActive;
        }

        public List<Order> getOrder() {
            return order;
        }

        public void setOrder(List<Order> order) {
            this.order = order;
        }
    }
}
