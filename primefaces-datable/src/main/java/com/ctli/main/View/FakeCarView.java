package com.ctli.main.View;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.ctli.main.model.FakeCar;
import com.ctli.main.repository.CarServices;

@ManagedBean(value = "FakeCarView")
public class FakeCarView {
	private List<com.ctli.main.model.FakeCar> cars;

	@Inject
	private CarServices service;

	@PostConstruct
	public void init() {
		cars = service.createCars(10);
	}

	public List<FakeCar> getCars() {
		return cars;
	}

	public void setService(CarServices service) {
		this.service = service;
	}
}
