package net.skhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.VO.Student;
import net.skhu.mapper.SearchMapper;
import net.skhu.model.Option;
import net.skhu.model.Pagination;

@Service
public class SearchService {

	@Autowired SearchMapper searchMapper;

	public List<Student> findAll(Pagination pagination) {

		int count = searchMapper.count(pagination);
        pagination.setRecordCount(count);

        return searchMapper.findAll(pagination);
    }

	public Option[] getOrderByOptions() {
        return SearchMapper.orderBy;
    }

    public Option[] getCategoryOptions() {
        return SearchMapper.category;
    }

}
