package com.osk.ctli.services;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.json.JSONObject;
import org.primefaces.shaded.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.osk.ctli.model.iUser;
import com.osk.ctli.repository.iUserRepository;
import com.osk.ctli.smsServices.AndroidPushNotificationsService;
import com.osk.ctli.smsServices.SmsPohService;

@Named
@ViewScoped
@ManagedBean(value = "iUserServices")
public class iUserServices implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<iUser> users;
	
	@Autowired
	iUserRepository iUserRepo;
	
	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;
	
	@Autowired
	SmsPohService pohService;

	public List<iUser> getUsers() {
		return users;
	}

	public void setUsers(List<iUser> users) {
		this.users = users;
	}
	
	@PostConstruct
	public void init() {
		users = iUserRepo.findAll();
	}
	
	public ResponseEntity<String> SendNoti(int id) throws JSONException {
		JSONObject body = new JSONObject();
		String selectToken = iUserRepo.findById(id).get().getToken();
		
		body.put("to", selectToken);
		body.put("priority", "high");
		
		JSONObject notification = new JSONObject();
		
		notification.put("title", "HelloNoob");
		notification.put("body", "UNoob");
		
		JSONObject data = new JSONObject();
		data.put("Key-1", "JSA Data 1");
		data.put("Key-2", "JSA Data 2");
		
		body.put("notification", notification);
		body.put("data", data);
		
		HttpEntity<String> request = new HttpEntity<String>(body.toString());
		
		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
		CompletableFuture.allOf(pushNotification).join();
		try {
			String firebaseResponse = pushNotification.get();
			return new ResponseEntity<String>(firebaseResponse,HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Push Notification Error!", HttpStatus.BAD_REQUEST);
	}
	
	public void sendSMS(String phnum) {
		pohService.sendSMS(phnum);
	}
}
