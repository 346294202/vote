package com.leoyon.vote.order;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {
	UNPAY(1, "未支付"),
	CANCELED(2, "已取消"),
	UNHANDLED(3, "未处理"),
	HANDLED(4, "已处理"),
	FINISHED(5, "已完成")
	;

	private int value;
	private String label;
	
	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	OrderStatus(int value,String label){
        this.value = value;
        this.label = label;
    }
	
    public static OrderStatus getByVal(int value){
        Optional<OrderStatus> optional =  Arrays.stream(OrderStatus.values()).filter(st -> st.value == value)
                .findFirst();
        return  optional.isPresent()?optional.get():null;
    }
}
