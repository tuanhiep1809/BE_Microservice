package com.example.channuoithaiviet.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Voucher;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateVoucherRequest;
import com.example.channuoithaiviet.repository.VoucherRepository;
import com.example.channuoithaiviet.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {

	@Autowired
    private VoucherRepository voucherRepository;
	
	 @Override
	    public List<Voucher> getList() {
	        // TODO Auto-generated method stub
	        return voucherRepository.findAll(Sort.by("id").descending());
	    }
	 @Override
	    public Voucher createVoucher(CreateVoucherRequest request) {
	        // TODO Auto-generated method stub
		 Voucher voucher = new Voucher();
		 voucher.setId(request.getId());
		 voucher.setName(request.getName());
		 voucher.setCount(request.getCount());
		 LocalDateTime hsd = request.getHsd();
		    if (hsd == null) {
		    	hsd = LocalDateTime.now().plusDays(30);
		    }
		 voucher.setHsd(hsd);
		 voucher.setEnable(true);
		 voucher.setMoney(request.getMoney());   
		 
		 voucherRepository.save(voucher);
	        return voucher;
	    }
	 @Override
	    public void enableVoucher(long id) {
	        // TODO Auto-generated method stub
		 Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
	        if(voucher.isEnable()){
	        	voucher.setEnable(false);
	        } else{
	        	voucher.setEnable(true);
	        }
	        voucherRepository.save(voucher);
	    }
	 
	 @Override
	    public void deleteVoucher(long id) {
	        // TODO Auto-generated method stub
		 Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
		 voucherRepository.delete(voucher);
	    }
	
	 @Override
	    public Voucher updateVoucher(long id, CreateVoucherRequest request) {
	        // TODO Auto-generated method stub
		 Voucher voucher= voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
		
		 voucher.setName(request.getName());
		 voucher.setCount(request.getCount());
		 LocalDateTime hsd = request.getHsd();
		    if (hsd == null) {
		    	hsd = LocalDateTime.now().plusDays(15);
		    }
		 voucher.setHsd(hsd);
		 voucher.setEnable(true);
		 voucher.setMoney(request.getMoney());	        
		 voucherRepository.save(voucher);

	        return voucher;
	    }
	 @Override
	    public Voucher updateVouchercount(long id, CreateVoucherRequest request) {
	        // TODO Auto-generated method stub
		 Voucher voucher= voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
		
		 
		 voucher.setCount(request.getCount());
		 LocalDateTime hsd = request.getHsd();
		    if (hsd == null) {
		    	hsd = LocalDateTime.now().plusDays(15);
		    }
		 voucher.setHsd(hsd);
		 voucher.setEnable(true);        
		 voucherRepository.save(voucher);

	        return voucher;
	    }
	 @Override
	    public List<Voucher> getListVoucher(long id) {
		 List<Voucher> list = voucherRepository.getListVoucher(id);
	        return list;
	    }
}
