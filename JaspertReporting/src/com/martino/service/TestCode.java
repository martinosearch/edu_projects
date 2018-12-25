package com.martino.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.martino.models.DataBean;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TestCode {

	public static void main(String[] args) {
		String sourceFileName = "design\\master_bull.jrxml";

		DataBeanList dataBeanList = new DataBeanList();
		List<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("TITRE_BULL", "Bulletin du Premier Trimestre");

		new JasperReportFill().createReport(sourceFileName, parameters, beanColDataSource);
	}

}
