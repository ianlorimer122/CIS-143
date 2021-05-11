package com.olympic.cis143.m04.student.tacotruck.set;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import com.olympic.cis143.m04.student.tacotruck.Orders;
import com.olympic.cis143.m04.student.tacotruck.TacoImpl;

public class OrdersSetImpl  implements Orders {
	private Set<Object> orders = new HashSet<Object>();
	private Queue<Object> queue = new ArrayDeque<Object>();
    @Override
    public void addOrder(TacoImpl tacoOrder) {
    	if(this.orders.add(tacoOrder))
    	{
    		queue.add(tacoOrder);
    	}
    	
    }

    @Override
    public boolean hasNext() {
        return !this.orders.isEmpty();
    }

    @Override
    public TacoImpl closeNextOrder() {
    	TacoImpl t = null;
		if(this.hasNext())
		{
			t = (TacoImpl) queue.remove();
			orders.remove(t);
			return t;
		}
		throw new RuntimeException("Order list is empty");
    }

    @Override
    public int howManyOrders() {
        return this.orders.size();
    }
}
