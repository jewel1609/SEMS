package com.ktds.sems.file.biz.impl;

import java.util.List;

import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.file.vo.FileVO;

public class FileBizImpl implements FileBiz {

	private FileDAO fileDAO;
	
	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	@Override
	public List<FileVO> getOneFileId(String educationId) {
		return fileDAO.getOneFileId(educationId);
	}

}
