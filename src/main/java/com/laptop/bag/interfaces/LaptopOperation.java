package com.laptop.bag.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.laptop.bag.model.LaptopDetails;

public class LaptopOperation implements Ioperation {
	
	private static List<LaptopDetails> details = new ArrayList<LaptopDetails>();
	
	@Override
	public List<LaptopDetails> getAllLaptops() {
		return details;
	}

	@Override
	public LaptopDetails addLaptopDetail(LaptopDetails laptop) {
		if(!details.contains(laptop))
			details.add(laptop);
		return laptop;
	}

	@Override
	public LaptopDetails updateLaptopDetail(LaptopDetails laptop) {
		LaptopDetails data = searchLaptop(laptop.getId());
		if(data != null){
			details.remove(data);
			details.add(laptop);
			return laptop;
		}
		return null;
	}

	@Override
	public int deleteLaptopBag(int id) {
		LaptopDetails data = searchLaptop(id);
		if(data != null){
			details.remove(data);
			return data.getId();
		}
		return -1;
	}

	@Override
	public LaptopDetails searchLaptop(int id) {
		for (LaptopDetails laptopDetails : details) {
			if(laptopDetails.getId() == id)
				return laptopDetails;
		}
		return null;
	}

	@Override
	public LaptopDetails searchLaptop(int id, String laptopName) {
		for (LaptopDetails laptopDetails : details) {
			if(laptopDetails.getId() == id && laptopName.equalsIgnoreCase(laptopDetails.getLaptopName()))
				return laptopDetails;
		}
		return null;
	}

}
