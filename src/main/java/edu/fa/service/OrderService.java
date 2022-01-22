package edu.fa.service;

import java.util.List;

import edu.fa.model.Admin;
import edu.fa.model.Order;

public interface OrderService {

	public List<Order> findAllOrder();
}
