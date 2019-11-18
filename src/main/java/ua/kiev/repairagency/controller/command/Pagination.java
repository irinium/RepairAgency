package ua.kiev.repairagency.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Pagination {
    private static final int RECORDS_PER_PAGE = 5;

    public void setPagination(HttpServletRequest request, int currentPage, int rows) {
        int nOfPages = rows / RECORDS_PER_PAGE;

        if (nOfPages % RECORDS_PER_PAGE > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);
    }
}
