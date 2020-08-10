1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59	<%@ tag description="pagination" pageEncoding="UTF-8"%>
<%@ tag import="java.util.ArrayList" %>
<%@ attribute name="recordCount" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" %>
<%@ attribute name="queryStringName" type="java.lang.String" required="true" %>
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
String name = (String)jspContext.getAttribute("queryStringName");

int currentPage = 1;
if (request.getParameter(name) != null)
    currentPage = Integer.parseInt(request.getParameter(name));

int pageCount = recordCount / pageSize;
if (pageCount * pageSize < recordCount) ++pageCount;

String queryString = request.getQueryString();
if (queryString == null)
    queryString = name + "=@@@";
else if (queryString.matches(".*" + name + "=[0-9]+.*"))
    queryString = queryString.replaceAll(name + "=[0-9]+", name + "=@@@");
else
    queryString = queryString + "&" + name + "=@@@";
String url = request.getAttribute("javax.servlet.forward.request_uri") + "?" + queryString;

if (currentPage > pageCount) currentPage = pageCount;
int base = ((currentPage - 1) / 10) * 10;

ArrayList<Page> pages = new ArrayList<Page>();
if (base > 0) pages.add(new Page(base, "&lt;"));
for (int i = 1; i <= 10; ++i) {
    int n = base + i;
    if (n > pageCount) break;
    pages.add(new Page(n, String.valueOf(n)));
}
int n = base + 11;
if (n <= pageCount)
    pages.add(new Page(n, "&gt;"));
%>
<ul class="pagination">
  <% for (Page p : pages) { %>
    <li class='<%= p.page == currentPage ? "active" : "" %>'>
        <a href='<%= url.replace("@@@", String.valueOf(p.page)) %>'><%= p.label %></a>
    </li>    
  <% } %>
</ul>