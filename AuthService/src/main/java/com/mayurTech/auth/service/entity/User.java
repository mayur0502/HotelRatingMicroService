package com.mayurTech.auth.service.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "userLoginInfo")
public class User {

	@Id
	@UuidGenerator
	private String userId;
	private String usrName;
	private String password;
}
