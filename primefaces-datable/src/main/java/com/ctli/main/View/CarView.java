package com.ctli.main.View;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;



import com.ctli.main.model.Car;
import com.ctli.main.repository.CarRepository;

@Named
@ManagedBean(value = "carView")
@ViewScoped
public class CarView implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CarRepository carRepository;

	private List<Car> cars;
	private List<Car> selectedCar;
	private Car selectedCars;
	@PostConstruct
	public void init() {
		cars = carRepository.findAll();
		System.out.println("cars:"+cars);
	}
	public List<Car> getCars() {
		return cars;
	}
	public List<Car> getSelectedCar() {
		return selectedCar;
	}
	public void setSelectedCar(List<Car> selectedCar) {
		this.selectedCar = selectedCar;
	}
	public Car getSelectedCars() {
		return selectedCars;
	}
	public void setSelectedCars(Car selectedCars) {
		this.selectedCars = selectedCars;
	}

}
