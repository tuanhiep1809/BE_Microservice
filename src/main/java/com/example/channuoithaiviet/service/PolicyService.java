package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.Policy;
import com.example.channuoithaiviet.model.request.CreatePolicyRequest;

public interface PolicyService {
	List<Policy> getList(); // lấy all
	List<Policy> getListPolicyBykieu(long id);  // lấy theo kiểu
	Policy getPolicy(long id);  // lấy theo id

	Policy createPolicy(CreatePolicyRequest request);  // thêm

	Policy updatePolicy(long id,CreatePolicyRequest request);  // sửa

    void deletePolicy(long id);   // xóa
    void enablePolicy(long id);// bật tắt trạng thái
}
