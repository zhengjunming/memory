package com.zhengjunming.www.controller.user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zhengjunming.www.dao.impl.OrderDaoImpl;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.service.store.StoreService;
import com.zhengjunming.www.util.UploadUtils;

/**
 * 评价订单控制器，可附带图片
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HandleEvaluateServlet")
public class HandleEvaluateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		Order order = new Order();
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		StoreService service = new StoreService();

		if (ServletFileUpload.isMultipartContent(request)) {
			// 构造工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小和临时目录
			factory.setSizeThreshold(1024 * 1024 * 8);// 8M 临时缓冲区（上传文件不大于8M
			// 不会产生临时文件）
			File repository = new File(getServletContext().getRealPath(
					"/picture/temp"));
			factory.setRepository(repository);// 当上传文件超过8M 会在临时目录中产生临时文件

			// 获得解析器
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 解决上传文件名 乱码问题
			upload.setHeaderEncoding("UTF-8");
			// 限制上传文件大小
			upload.setFileSizeMax(1024 * 1024 * 72);

			// 对请求体内容进行解析
			try {
				List<FileItem> list = upload.parseRequest(request);
				// 遍历FileItem 集合
				for (FileItem fileItem : list) {
					// 判断每个FileItem 是不是文件上传项
					if (fileItem.isFormField()) {
						// 不是上传文件
						String name = fileItem.getFieldName(); // name属性
						String value = fileItem.getString("UTF-8"); // value 属性
						if (name.equals("evaluate")) {
							order.setEvaluation(value);
						} else if (name.equals("id")) {
							try {
								id = Integer.parseInt(value);
								order.setId(id);
							} catch (NumberFormatException e) {
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else {
						String filename = fileItem.getName(); // 文件名

						// 判断是否为空
						if (filename == "") {
							// 添加评价
							order.setPicture(filename);
							try {
								orderDaoImpl.addEvaluate(order);
							} catch (Exception e) {
								e.printStackTrace();
							}
							response.setHeader(
									"refresh",
									"2;URL=//zhengjunming_user_commodity_trading_system/UserListEvaluatedOrderServlet");
							response.getWriter()
									.println(
											"<center><h1>评价成功，2秒后转向评价订单页面</h1></center>");
							return;
						}

						// 判断格式是否正确
						try {
							if (service.imageFormatIsRight(filename) == StoreService.IMAGE_FORMAT_IS_ERROR) {
								request.setAttribute("message", "图片格式不正确");
								request.getRequestDispatcher(
										"/user/evaluateOrder.jsp").forward(
										request, response);
								return;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						// 符合条件
						// 解决老版本浏览器IE6 文件路径存在问题
						if (filename.contains("\\")) {
							filename = filename.substring(filename
									.lastIndexOf("\\") + 1);
						}
						InputStream in = new BufferedInputStream(
								fileItem.getInputStream()); // 文件内容

						// 保证上传文件名唯一
						filename = UUID.randomUUID().toString() + filename;

						// 生成随机目录
						String randomPath = UploadUtils
								.generateRandomDir(filename);// 生成目录不一定存在 ---创建
						File path = new File(getServletContext().getRealPath(
								"/picture/upload" + randomPath));
						String pathname = path.toString().replace("\\", "/")
								+ "/" + filename;
						String picturepath = pathname
								.substring(pathname
										.indexOf("/zhengjunming_user_commodity_trading_system"));// 获得商品图片所在目录
						path.mkdirs();
						order.setPicture(picturepath); // 存图片路径
						// 将文件内容输出WEB-INF/upload 目录
						File targetFile = new File(path, filename);
						OutputStream out = new BufferedOutputStream(
								new FileOutputStream(targetFile));
						int temp;
						while ((temp = in.read()) != -1) {
							out.write(temp);
						}
						out.close();
						in.close();

						// 删除临时文件(必须将文件流关掉)
						fileItem.delete();
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		try {
			orderDaoImpl.addEvaluate(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader(
				"refresh",
				"2;URL=//zhengjunming_user_commodity_trading_system/UserListEvaluatedOrderServlet");
		response.getWriter().println(
				"<center><h1>评价成功，2秒后转向评价订单页面</h1></center>");
	}

}
