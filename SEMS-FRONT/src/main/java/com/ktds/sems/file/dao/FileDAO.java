package com.ktds.sems.file.dao;

import java.util.List;

import com.ktds.sems.file.vo.FileVO;

public interface FileDAO {

	public List<FileVO> getOneFileId(String educationId);

}