package com.ktds.sems.file.biz;

import java.util.List;

import com.ktds.sems.file.vo.FileVO;

public interface FileBiz {

	public List<FileVO> getOneFileId(String educationId);

}
