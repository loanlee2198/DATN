package edu.fa.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fa.dto.NewBookDto;
import edu.fa.dto.OrderDto;
import edu.fa.dto.BookDto;
import edu.fa.dto.RevenueDto;
import edu.fa.model.Counter;
import edu.fa.model.Customer;
import edu.fa.model.Order;
import edu.fa.model.OrderBook;
import edu.fa.model.Book;
import edu.fa.model.StatisticalConditions;
import edu.fa.model.Type;
import edu.fa.service.AdminService;
import edu.fa.service.UserService;

@Controller
@RequestMapping(value = "")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@ModelAttribute("newBook")
	public NewBookDto newBookDto() {
		return new NewBookDto();
	}

	@ModelAttribute("newType")
	public Type newBookType() {
		return new Type();
	}

	
	@ModelAttribute("updateBook")
	public Book book() {
		return new Book();
	}

	@GetMapping(value = { "/homeAdmin" })
	public String home( HttpSession httpSession, Model model) {
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		List<Book> books = userService.findAllBook(false);
		model.addAttribute("counter", new Counter());
		model.addAttribute("books", books);
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			return "admin/new";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping(value = "/admin/create")
	public String showNewBook(HttpServletRequest request, HttpSession httpSession, Model model) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		if (c != null) {
			return "admin/taoMoiSanPham";
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping(value = "/admin/createType")
	public String showNewType(HttpServletRequest request, HttpSession httpSession, Model model) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		if (c != null) {
			return "admin/createType";
		} else {
			return "redirect:/login";
		}
	}


	@PostMapping(value = "/admin/create", params = "create")
	public String createNewBook(@ModelAttribute("newBook") NewBookDto newBookDto,
			HttpServletRequest request, HttpSession httpSession) {

		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			adminService.createNewBook(newBookDto);
			return "redirect:/admin/create?success";
		} else {
			return "redirect:/login";
		}

	}
	@PostMapping(value = "/admin/createType", params = "create")
	public String createNewTypeBook(@ModelAttribute("newType") Type newType,
			HttpServletRequest request, HttpSession httpSession) {

		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			adminService.createNewTypeBook(newType);
			return "redirect:/admin/createType?success";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/bookList")
	public String getBookList(Model model, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			List<Book> books = adminService.findAllBook(false);
			model.addAttribute("books", books);
			return "admin/danhSachSanPham";
		} else {
			return "redirect:/login";
		}

	}

	@PostMapping(value = "/admin/bookDetail{id}", params = "update")
	public String updateBook(@ModelAttribute("updateBook") Book book, HttpServletRequest request,
			HttpSession httpSession) {
		Book book1 = adminService.findBookById(book.getId());
		System.out.println(book.getImage());
		if (book.getImage() == "") {
			book.setImage(book1.getImage());

		}
		System.out.println(book.toString());
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {

			adminService.save(book);
			return "redirect:/admin/bookDetail{id}?success";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping(value = "/admin/bookDetail{id}", params = "exit")
	public String exit(HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			return "redirect:/admin/bookList";
		} else {
			return "redirect:/login";
		}

	}

	@GetMapping("/admin/bookDetail{id}")
	public String detail(@PathVariable(name = "id") Integer id, Model model, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			httpSession.getAttribute("admin");
			Book book = adminService.findBookById(id);
			model.addAttribute("book", book);
			return "admin/chiTietSanPham";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/delete{id}")
	public String delete(@PathVariable(name = "id") Integer id, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			Book book = adminService.findBookById(id);
			book.setDelete(true);
			adminService.save(book);
			return "redirect:/admin/bookList";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/orderList")
	public String getOrderList(Model model, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			List<Object[]> obj = adminService.findAllOrder();
			Date dayStart = Date.valueOf("2020-01-01");
			Date dayEnd = new Date(System.currentTimeMillis());

			List<OrderDto> orders = new ArrayList<OrderDto>();
			for (Object[] item : obj) {
				OrderDto dto = new OrderDto();
				dto.setId((Integer) item[0]);
				dto.setDate((Date) item[1]);
				dto.setFullname((String) item[2]);
				dto.setTotal((Integer) item[3]);

				dto.setStatus(((Boolean) item[4]) ? "Đã giao" : "Chưa giao");
				orders.add(dto);
			}
			model.addAttribute("orders", orders);
			model.addAttribute("dayStart", dayStart);
			model.addAttribute("dayEnd", dayEnd);
			return "admin/danhSachDatHang";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/revenue")
	public String getRevenue(Model model, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		List<String> time = new ArrayList<String>();
		time.addAll(Arrays.asList("Theo ngày", "Theo tháng", "Theo năm"));
		httpSession.setAttribute("time", time);
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);

		List<Object[]> obj = new ArrayList<>();
		if (c != null) {
			obj = adminService.getRevenueDay();

			List<RevenueDto> revenueList = new ArrayList<RevenueDto>();
			for (Object[] item : obj) {
				RevenueDto dto = new RevenueDto();
				dto.setDate((String) item[0]);
				dto.setRevenue((Integer) item[1]);
				dto.setProfit((Integer) item[2]);
				revenueList.add(dto);
			}
			model.addAttribute("revenueList", revenueList);
			return "admin/doanhthu";
		} else {
			return "redirect:/login";
		}

	}

	@ModelAttribute("condition")
	public StatisticalConditions i() {
		return new StatisticalConditions();
	}

	@RequestMapping("/admin/revenue/statistics")
	public String getRevenueStatistics(Model model, @ModelAttribute("condition") StatisticalConditions condition,
			HttpServletRequest request, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		List<String> time = (List<String>) httpSession.getAttribute("time");
		List<Object[]> obj = new ArrayList<>();
		System.out.println(condition.getTime());
//		System.out.println(time.get(0));
		System.out.println(condition.getTime().equals(time.get(0)));
		if (c != null) {

			if (condition.getTime().equals(time.get(0))) {
				obj = adminService.getRevenueDay();
			}
			if (condition.getTime().equals(time.get(1))) {
				obj = adminService.getRevenueMonth();
			}
			if (condition.getTime().equals(time.get(2))) {
				obj = adminService.getRevenueYear();
			}

			List<RevenueDto> revenueList = new ArrayList<RevenueDto>();
			for (Object[] item : obj) {
				RevenueDto dto = new RevenueDto();
				dto.setDate((String) item[0]);
				dto.setRevenue((Integer) item[1]);
				dto.setProfit((Integer) item[2]);

				revenueList.add(dto);
			}
			model.addAttribute("revenueList", revenueList);
			return "admin/doanhthu";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/orderDetail{id}")
	public String getBookList(Model model, @PathVariable(name = "id") String orderId, HttpServletRequest request,
			HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			List<Object[]> obj = adminService.findBookOfOrder(orderId);
			Date date = (Date) obj.get(0)[1];
			Integer customerId = (Integer) obj.get(0)[2];
			Customer customer = adminService.findCustomerById(customerId);
			int total = 0;
			List<BookDto> books = new ArrayList<BookDto>();
			for (Object[] item : obj) {
				BookDto dto = new BookDto();
				dto.setNameBook((String) item[3]);
				dto.setQuanity((Integer) item[4]);
				dto.setPrice((Integer) item[5]);
				total += (Integer) item[4] * (Integer) item[5];
				books.add(dto);
			}

			books.forEach(System.out::println);
			model.addAttribute("date", date);
			model.addAttribute("customer", customer);
			model.addAttribute("books", books);
			model.addAttribute("orderId", orderId);
			model.addAttribute("total", total);
			model.addAttribute("status", ((Boolean) obj.get(0)[6]) == false ? "Chưa giao" : "Đã giao");
			return "admin/orderDetail";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/orderDetail/statistics")
	public String getStatistics(Model model, @RequestParam(name = "day-start") Date dayStart,
			@RequestParam(name = "day-end") Date dayEnd, HttpServletRequest request, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {

			List<Object[]> obj = adminService.findOrderBetweenTwoDate(dayStart, dayEnd);
			List<OrderDto> orders = new ArrayList<OrderDto>();
			for (Object[] item : obj) {
				OrderDto dto = new OrderDto();
				dto.setId((Integer) item[0]);
				dto.setDate((Date) item[1]);
				dto.setFullname((String) item[2]);
				dto.setTotal((Integer) item[3]);
				dto.setStatus(((Boolean) item[4]) ? "Đã giao" : "Chưa giao");
				orders.add(dto);
			}
			orders.forEach(System.out::println);
			model.addAttribute("orders", orders);
			model.addAttribute("dayStart", dayStart);
			model.addAttribute("dayEnd", dayEnd);

			return "admin/danhSachDatHang";
		} else {
			return "redirect:/login";
		}

	}

	@PostMapping(value = "/admin/orderDetail{orderId}", params = "update")
	public String updateOrder(@PathVariable(name = "orderId") Integer id, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			Order order = adminService.findOrderById(id);
			order.setStatus(true);
			adminService.saveOrder(order);
			return "redirect:/admin/orderDetail" + id;
		} else {
			return "redirect:/login";
		}

	}

	@PostMapping(value = "/admin/orderDetail{orderId}", params = "delete")
	public String deleteOrder(@PathVariable(name = "orderId") Integer id, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			Order order = adminService.findOrderById(id);
			List<OrderBook> orderBooks = adminService.findAllOrderBookById(id);
			orderBooks.forEach(System.out::println);
			for (OrderBook orderBook : orderBooks) {
				adminService.deleteOrderBook(orderBook);
			}
			adminService.deleteOrder(order);
			return "redirect:/admin/orderList";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping("/admin/customerList")
	public String getCustomerList(Model model, HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("admin");
		if (c != null) {
			List<Customer> customers = adminService.findAllCustomer();
			Date dayStart = Date.valueOf("2020-01-01");
			Date dayEnd = new Date(System.currentTimeMillis());

			model.addAttribute("customers", customers);
			model.addAttribute("dayStart", dayStart);
			model.addAttribute("dayEnd", dayEnd);
			return "admin/danhSachKhachHang";
		} else {
			return "redirect:/login";
		}

	}

}