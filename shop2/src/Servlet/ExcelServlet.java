package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import entity.Msg;
import entity.Sales;
import service.ISalesService;
import service.impl.SalesServiceImpl;

@WebServlet("/ExcelServlet")
public class ExcelServlet extends HttpServlet {
	static ISalesService salesService = new SalesServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			//创建Excel文件的对象工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//创建工作表
			HSSFSheet sheet = workbook.createSheet("销售表");
			//创建首行
			HSSFRow row = sheet.createRow(0);
			//设置表头
			String[] title = { "单号", "商品编号", "数量", "总金额", "销售日期", "员工ID" };
			//赋值给表头
			for (int i = 0; i < title.length; i++) {
				row.createCell(i).setCellValue(title[i]);
			}
			
			// 获取数据
			Msg msg = salesService.getSales();
			List<Sales> sales = (List<Sales>) msg.getObj();
			for (int i = 0; i < sales.size(); i++) {
				//创建行
				HSSFRow rows = sheet.createRow(i+1);
				//设置每行单元格的内容
				String[] content = {String.valueOf(sales.get(i).getDanhao()),
						String.valueOf(sales.get(i).getGs_id()),
						String.valueOf(sales.get(i).getNum()),
						String.valueOf(sales.get(i).getPrice()),
						sales.get(i).getSelldate(),
						String.valueOf(sales.get(i).getPs_id())};
				for (int j = 0; j < content.length; j++) {
					rows.createCell(j).setCellValue(content[j]);
				}
			}
			//文件名称
			String fileName = "销售表"+System.currentTimeMillis()+".xls";
			//设置fileName的编码
			String resetName = new String(fileName.getBytes(),"ISO-8859-1");
			//设置为excel表格格式
			resp.setContentType("application/vnd.ms-excel");
			//设置响应头，设置文件的名字
			resp.setHeader("Content-Disposition", "attachment;filename="+resetName);
			//创建输出流
			ServletOutputStream outputStream = resp.getOutputStream();
			//将工作簿写入输出流
			workbook.write(outputStream);
			//关闭流
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
