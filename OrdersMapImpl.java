package com.olympic.cis143.m04.student.homework.tacotruckmap.impl;

import com.olympic.cis143.m04.student.homework.tacotruckmap.OrderDoesNotExistException;
import com.olympic.cis143.m04.student.homework.tacotruckmap.Orders;
import com.olympic.cis143.m04.student.homework.tacotruckmap.TacoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersMapImpl implements Orders {
	private Map<String, List<TacoImpl>> orders = new HashMap<>();

    @Override
    public void createOrder(final String orderid) {
    	// Assume id is not a duplicate
    	this.orders.put(orderid, new ArrayList<TacoImpl>());
    }

    @Override
    public void addTacoToOrder(final String orderid, final TacoImpl taco) throws OrderDoesNotExistException {
    	if(this.orders.containsKey(orderid))
    	{
    		this.orders.get(orderid).add(taco);
    		return;
    	}
    	throw new OrderDoesNotExistException("Invalid Order ID: " + orderid);
    }

    @Override
    public boolean hasNext() {
        return !this.orders.isEmpty();
    }

    @Override
    public List<TacoImpl> closeOrder(final String orderid) throws OrderDoesNotExistException {
    	if(this.orders.containsKey(orderid))
    	{
    		return this.orders.remove(orderid);
    	}
    	throw new OrderDoesNotExistException("Invalid Order ID: " + orderid);
    }

    @Override
    public int howManyOrders() {
        return this.orders.size();
    }

    @Override
    public List<TacoImpl> getListOfOrders(final String orderid) throws OrderDoesNotExistException {
    	if(this.orders.containsKey(orderid))
    	{
    		return this.orders.get(orderid);
    	}
    	throw new OrderDoesNotExistException("Invalid Order ID: " + orderid);
    }
}
