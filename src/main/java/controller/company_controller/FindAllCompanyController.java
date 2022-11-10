package controller.company_controller;

import config.HibernateProvider;
import model.dto.CompanyDto;
import repository.CompanyRepository;
import service.CompanyService;
import service.converter.CompanyConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/companies/all")
public class FindAllCompanyController extends HttpServlet {
    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        CompanyRepository companyRepository = new CompanyRepository(dbProvider);
        CompanyConverter companyConverter = new CompanyConverter();
        companyService = new CompanyService(companyRepository, companyConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyDto> companies = companyService.findAll();
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/jsp/companies/findAllCompany.jsp").forward(req, resp);
    }
}
