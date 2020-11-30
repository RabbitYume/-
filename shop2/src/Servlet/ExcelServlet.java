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
			//����Excel�ļ��Ķ�������
			HSSFWorkbook workbook = new HSSFWorkbook();
			//����������
			HSSFSheet sheet = workbook.createSheet("���۱�");
			//��������
			HSSFRow row = sheet.createRow(0);
			//���ñ�ͷ
			String[] title = { "����", "��Ʒ���", "����", "�ܽ��", "��������", "Ա��ID" };
			//��ֵ����ͷ
			for (int i = 0; i < title.length; i++) {
				row.createCell(i).setCellValue(title[i]);
			}
			
			// ��ȡ����
			Msg msg = salesService.getSales();
			List<Sales> sales = (List<Sales>) msg.getObj();
			for (int i = 0; i < sales.size(); i++) {
				//������
				HSSFRow rows = sheet.createRow(i+1);
				//����ÿ�е�Ԫ�������
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
			//�ļ�����
			String fileName = "���۱�"+System.currentTimeMillis()+".xls";
			//����fileName�ı���
			String resetName = new String(fileName.getBytes(),"ISO-8859-1");
			//����Ϊexcel����ʽ
			resp.setContentType("application/vnd.ms-excel");
			//������Ӧͷ�������ļ�������
			resp.setHeader("Content-Disposition", "attachment;filename="+resetName);
			//���������
			ServletOutputStream outputStream = resp.getOutputStream();
			//��������д�������
			workbook.write(outputStream);
			//�ر���
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
