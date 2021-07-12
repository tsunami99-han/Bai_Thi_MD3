package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO=new ProductDAO();
    CategoryDAO categoryDAO=new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "list" :
                    showListPage(request, response);
                    break;
                case "save" :
                    saveEdit(request, response);
                    break;
                case "create" :
                    showCreatePage(request, response);
                    break;
                case "delete":
                    deleteById(request, response);
                    break;
                case "edit":
                    showEditPage(request, response);
                    break;
                case "search":
                    findByName(request, response);
                    break;
                default:
                    showListPage(request, response);
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "list" :
                    showListPage(request, response);
                    break;
                case "create" :
                    createProduct(request, response);
                    break;
                case "save" :
                    saveEdit(request, response);
                    break;
                default:
                    showListPage(request, response);
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showEditPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt( request.getParameter("id"));
        Product product= productDAO.findById(id);
        List<Category> categoryList=categoryDAO.findAll();
        request.setAttribute("product",product);
        request.setAttribute("categoryList",categoryList);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("product/edit.jsp");
        requestDispatcher.forward(request,response);
    }
    public void findByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("key");
        List<Product> productList= productDAO.findByName(name);
        List<Category> categoryList=new ArrayList<>();
        for (int i = 0;i<productList.size();i++){
            categoryList.add(categoryDAO.findById(productList.get(i).getCategory()));
        }
        request.setAttribute("listProduct",productList);
        request.setAttribute("listCategory",categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(request,response);
    }
    public void saveEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String desc= request.getParameter("desc");
        int category = Integer.parseInt(request.getParameter("category"));
        Product product=new Product(name,price,quantity,color,desc,category);
        productDAO.edit(id,product);
        showListPage(request, response);
    }
    public void showListPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Product> productList= productDAO.findAll();
        List<Category> categoryList=new ArrayList<>();
        for (int i = 0;i<productList.size();i++){
            categoryList.add(categoryDAO.findById(productList.get(i).getCategory()));
        }
        request.setAttribute("listProduct",productList);
        request.setAttribute("listCategory",categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(request,response);
    }
    public void showCreatePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Category> list = categoryDAO.findAll();
        request.setAttribute("list",list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        requestDispatcher.forward(request,response);
    }
     public void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String desc= request.getParameter("desc");
        int category = Integer.parseInt(request.getParameter("category"));
        Product product=new Product(name,price,quantity,color,desc,category);
        productDAO.add(product);
        showListPage(request, response);
     }
     public void deleteById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        productDAO.delete(id);
        showListPage(request,response);
     }
}
