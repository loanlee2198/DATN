
package edu.fa.controller;

import java.util.Date;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.fa.dto.CustomerForgotPasswordDto;
import edu.fa.dto.CustomerRegisterDto;
import edu.fa.dto.OrderBookDto;
import edu.fa.dto.OrderByMeDto;
import edu.fa.dto.OrderDto;
import edu.fa.dto.BookCartDto;
import edu.fa.model.Counter;
import edu.fa.model.Customer;
import edu.fa.model.Order;
import edu.fa.model.OrderBook;
import edu.fa.model.Book;
import edu.fa.model.Type;
import edu.fa.repository.TypeRepository;
import edu.fa.service.UserService;

@Controller
@RequestMapping(value = "") // x
public class UserController {

	@Autowired
	UserService userService;
	Integer total = 0;

	@Autowired
	List<TypeRepository> typeRepository;

	List<Book> bookCart = new ArrayList<Book>();
	List<BookCartDto> cart = new ArrayList<BookCartDto>();
	List<OrderBookDto> bookList = new ArrayList<OrderBookDto>();

	@ModelAttribute("cart")
	public List<BookCartDto> bookCartDto() {
		return cart;
	}

	@ModelAttribute("bookCart")
	public List<Book> ptoducts() {
		return bookCart;
	}

	@ModelAttribute("booklist")
	public List<Book> book() {
		return bookCart;
	}

	@ModelAttribute("customer")
	public Customer customer() {
		return new Customer();
	}

	@ModelAttribute("customerPass")
	public CustomerForgotPasswordDto pass() {
		return new CustomerForgotPasswordDto();
	}

	@GetMapping(value = { "/home", "/" })
	public String home(HttpSession httpSession, Model model) {
		List<Book> books = userService.findAllBook(false);
		model.addAttribute("counter", new Counter());
		List<Type> types = userService.findAll();

		model.addAttribute("types", types);
		model.addAttribute("books", books);
		String b = (String) httpSession.getAttribute("b");
		model.addAttribute("b", b);
		return "user/home";
	}

	@RequestMapping("/login")
	public String showLoginForm(Model model) {
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		System.out.println("LOAN");

		return "user/login";
	}

	@RequestMapping("/introduce")
	public String introduce(Model model) {
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);

		return "user/gioithieu";
	}

	@RequestMapping("/forgotPassword")
	public String forgotPassword(Model model) {
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		return "user/forgotPassword";
	}

	@RequestMapping(value = "/forgotPassword", params = "request")
	public String requestForgotPassword(@ModelAttribute("customerPass") CustomerForgotPasswordDto customerPass,
			HttpSession httpSession, Model model) {
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		System.out.println(customerPass.toString());
		Customer customer = userService.findCustomerByUsernameAndPhone(customerPass);
		if (customer != null) {
			String pass = customer.getPassword();
			customer.setPassword("loan123");
			userService.update(customer);
			return "redirect:/forgotPassword?success";

		} else {
			return "redirect:/forgotPassword?err";
		}
	}

	@RequestMapping(value = "/personalInformation", params = "back")
	public String exit(HttpSession httpSession) {
		Customer c = (Customer) httpSession.getAttribute("user");
		if (c != null) {
			return "redirect:/home";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "/personalInformation", params = "update")
	public String updatePersonalInformation(@ModelAttribute("customer") Customer customer, HttpSession httpSession,
			Model model) {
		List<Type> types = userService.findAll();
		httpSession.setAttribute("user", customer);
		model.addAttribute("types", types);

		Customer c = (Customer) httpSession.getAttribute("user");

		if (c == null) {
			return "redirect:/login?error";///
		} else {
			userService.update(customer);
			return "redirect:/personalInformation?success";
		}
	}

	@RequestMapping("/personalInformation")
	public String personalInformation(HttpSession httpSession, Model model) {
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		Customer customer = (Customer) httpSession.getAttribute("user");
		System.out.println(customer.toString());

		Customer c = (Customer) httpSession.getAttribute("user");

		if (c == null) {
			return "redirect:/login?error";///
		} else {
			model.addAttribute("customer", customer);

			return "user/personalInformation";
		}
	}

	@PostMapping("/login")
	public String registerUserAccount(HttpSession httpSession, Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		Customer customer = userService.findByUsernameAndPassword(username, password);

		if (customer == null) {
			return "redirect:/login?error";///
		} else {
			httpSession.setAttribute("username", customer.getUsername());
			if (customer.getRole().equals("ROLE_ADMIN")) {
				System.out.println("===========================Loan");
				httpSession.setAttribute("admin", customer);
				return "redirect:/homeAdmin";
			} else {
				httpSession.setAttribute("user", customer);

				List<Object[]> orderBook = userService.findOrderByYour(customer.getId());
				if (orderBook != null) {
					String b = new String();
					Date date = new Date();
					List<OrderByMeDto> books = new ArrayList<OrderByMeDto>();
					for (Object[] item : orderBook) {
						date = (Date) item[1];
						OrderByMeDto dto = new OrderByMeDto();
						dto.setId((Integer) item[0]);
						dto.setDate((Date) item[1]);
						dto.setBookName((String) item[2]);
						dto.setPrice((Integer) item[3]);

						b = b + item[2] + " ";
						System.out.println(item[2]);
						books.add(dto);
					}
					Date date2 = new Date();

					long getDiff = date2.getTime() - date.getTime();

					long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
					if (getDaysDiff > 20) {
						httpSession.setAttribute("b", b+" còn "+(30-getDaysDiff)+" ngày mượn");
					}else {
						httpSession.setAttribute("b", "");
					}
				}

				return "redirect:/home";
			}
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession httpSession, Model model) {
		httpSession.invalidate();
		return "redirect:/home";
	}

	@GetMapping("/type{id}")
	public ModelAndView type(HttpSession httpSession, @PathVariable(name = "id") Integer id) {
		ModelAndView editview = new ModelAndView("user/type");

		List<Type> types = userService.findAll();
		List<Book> books = userService.findAllBook(false);

		List<Book> newBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getTypeId() == id) {
				newBooks.add(book);
			}
		}
		editview.addObject("newBooks", newBooks);
		editview.addObject("type", types.get(id - 1));
		editview.addObject("types", types);

		return editview;
	}

	@GetMapping("/detail/{id}")
	public ModelAndView detail(HttpSession httpSession, @PathVariable(name = "id") Integer id) {
		ModelAndView editview = new ModelAndView("user/chiTietSanPham");
		Book book = userService.findBookById(id);
		editview.addObject("book", book);
		List<Type> types = userService.findAll();
		editview.addObject("types", types);

		return editview;

	}

	@GetMapping(value = { "/search" })
	public String search(HttpSession httpSession, @RequestParam("key") String surname, Model model) {

		Set<Book> books = userService.search(surname);
		System.out.println(books.size());
		List<Type> types = userService.findAll();
		if (books.size() == 0) {
			return "redirect:/search-error";
		} else {
			model.addAttribute("types", types);
			model.addAttribute("books", books);
			model.addAttribute("keyword", surname);
			System.out.println(surname);
			return "user/searchResult";
		}
	}

	@GetMapping(value = { "/search-error" })
	public String error(Model model) {
		List<Type> types = userService.findAll();
		model.addAttribute("types", types);
		return "user/searchError";
	}

	@PostMapping(value = "/detail/{id}", params = "add")
	public String addBook(HttpSession httpSession, @PathVariable(name = "id") Integer id) {
		ModelAndView editview = new ModelAndView("user/gioHang");
		List<Type> types = userService.findAll();
		editview.addObject("types", types);
		List<OrderBookDto> prolist = bookList;
		Book newBook = userService.findBookById(id);
		OrderBookDto dto = new OrderBookDto(newBook, 1);

		List<Integer> bookIdList = new ArrayList<>();
		for (OrderBookDto opd : prolist) {
			bookIdList.add(opd.getBook().getId());
		}
		System.out.println("jjj");
		System.out.println("asas " + bookIdList);
		if (bookIdList.contains(id)) {
			int index = bookIdList.indexOf(id);

			httpSession.setAttribute("", bookIdList);
			httpSession.removeAttribute("");
			prolist.get(index).setQuantity(1 + prolist.get(index).getQuantity());
		} else {
			prolist.add(dto);
		}
		total += newBook.getPrice() * 1;

		editview.addObject("bookCart", prolist);
		editview.addObject("total", total);
		return "redirect:/cart";

	}

	@RequestMapping(value = "/user/orderSuccess")
	public String buyBook(HttpSession httpSession, HttpServletRequest request) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		Customer customer = (Customer) httpSession.getAttribute("user");

		if (customer != null) {
			Order order = new Order(customer.getId(), date, false);
			userService.addOrder(order);
			order = userService.findOrderByCustomerId(customer.getId());

			List<OrderBookDto> books = bookList;
			System.out.println(books.size());
			if (books.size() != 0) {
				for (OrderBookDto item : books) {
					OrderBook op = new OrderBook(order.getId(), item.getBook().getId(), item.getQuantity(),
							item.getBook().getPrice());
					userService.save(op);
				}

				bookList.removeAll(books);
				total = 0;
				return "redirect:/home";
			} else {
				return "redirect:/cart";
			}

		} else {
			return "redirect:/login";
		}

	}

	@PostMapping(value = "/cart/{id}", params = "remove")
	public String removeBookInCart(HttpSession httpSession, @PathVariable(name = "id") Integer id) {
		ModelAndView editview = new ModelAndView("user/gioHang");
		List<Type> types = userService.findAll();
		editview.addObject("types", types);
		System.out.println(id);

		List<OrderBookDto> prolist = bookList;

//		List<Integer> bookIdList = prolist.stream().map(OrderBookDto::getBook).toList().stream()
//				.map(Book::getId).toList();
		List<Integer> bookIdList = new ArrayList<>();

		for (OrderBookDto opd : prolist) {
			Integer bookId = opd.getBook().getId();
			bookIdList.add(bookId);
		}

		int index = bookIdList.indexOf(id);

		bookList.remove(index);
		prolist.forEach(System.out::println);
		total = 0;
		for (OrderBookDto book : prolist) {
			total += book.getQuantity() * book.getBook().getPrice();
		}

		System.out.println(total);
		editview.addObject("bookCart", prolist);
		editview.addObject("total", total);
		return "redirect:/cart";

	}

	@PostMapping(value = "/cart", params = "update")
	public String update(HttpSession httpSession, @RequestParam(name = "id") Integer id,
			@RequestParam("quantity") Integer quantity) {
		ModelAndView editview = new ModelAndView("user/gioHang");
		List<Type> types = userService.findAll();
		editview.addObject("types", types);

		List<OrderBookDto> prolist = bookList;

//		List<Integer> bookIdList = prolist.stream().map(OrderBookDto::getBook).toList().stream()
//				.map(Book::getId).toList();
		List<Integer> bookIdList = new ArrayList<>();
		for (OrderBookDto opd : prolist) {
			bookIdList.add(opd.getBook().getId());
		}

		int index = bookIdList.indexOf(id);

		prolist.get(index).setQuantity(quantity);
		prolist.forEach(System.out::println);
		total = 0;
		for (OrderBookDto book : prolist) {
			total += book.getQuantity() * book.getBook().getPrice();
		}

		System.out.println(total);
		editview.addObject("bookCart", prolist);
		editview.addObject("total", total);
		return "redirect:/cart";

	}

	@GetMapping("/cart")
	public ModelAndView cart(HttpSession httpSession) {
		ModelAndView editview = new ModelAndView("user/gioHang");

		List<OrderBookDto> prolist = bookList;

		List<Type> types = userService.findAll();
		editview.addObject("types", types);
		editview.addObject("bookCart", prolist);
		editview.addObject("total", total);
		return editview;

	}
}
