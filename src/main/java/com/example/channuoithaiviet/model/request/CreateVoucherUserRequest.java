package com.example.channuoithaiviet.model.request;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.entity.Voucher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVoucherUserRequest {
	
	private long id;
	private LocalDateTime createAt;
    private long userId;
    private long voucherId;
}
