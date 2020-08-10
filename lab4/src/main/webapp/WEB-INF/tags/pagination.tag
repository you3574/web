<%@ tag description="pagination" pageEncoding="UTF-8"%>
<%@ tag import="java.util.ArrayList" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" %>
<%@ attribute name="recordCount" type="java.lang.Integer" required="true" %>
<%! 
private class Page { 
    int page; 
    String label;
    
    Page(int page, String label) {
        this.page = page;
        this.label = label;
    }
} 
%>
<%
int recordCount = (Integer)jspContext.getAttribute("recordCount");
int pageSize = (Integer)jspContext.getAttribute("pageSize");

int current = 1;
if (request.getParameter("now") != null)
    current = Integer.parseInt(request.getParameter("now"));

int pageCount = recordCount / pageSize;
if (pageCount * pageSize < recordCount) ++pageCount;

String queryString = request.getQueryString();
if (queryString == null)
    queryString = "now=@@@";
else if (queryString.matches(".*now=[0-9]+.*"))
    queryString = queryString.replaceAll("now=[0-9]+", "now=@@@");
else
    queryString = queryString + "&now=@@@";
String url = request.getAttribute("javax.servlet.forward.request_uri") + "?" + queryString;

if (current > pageCount) current = pageCount;
int base = ((current - 1) / 5) * 5;

ArrayList<Page> pages = new ArrayList<Page>();
if (base > 0) pages.add(new Page(base, "&lt;"));
for (int i = 1; i <= 5; ++i) {
    int n = base + i;
    if (n > pageCount) break;
    pages.add(new Page(n, String.valueOf(n)));
}
int n = base + 6;
if (n <= pageCount)
    pages.add(new Page(n, "&gt;"));
%>
<ul class="pagination">
  <% for (Page p : pages) { %>
    <li class='<%= p.page == current ? "active" : "" %>'>
        <a href='<%= url.replace("@@@", String.valueOf(p.page)) %>'><%= p.label %></a>
    </li>    
  <% } %>
</ul>
