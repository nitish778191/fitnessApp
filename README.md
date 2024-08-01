from jinja2 import Template

# Example data (abbreviated for brevity)
data = {
    "parent": {
        "ip": "192.168.1.1",
        "first_seen": "2024-08-01",
        "last_seen": "2024-08-02",
        "seen": 10,
        "actor": "Unknown",
        "spoofable": False,
        "classification": "Malicious",
        "cve": "CVE-2023-12345",
        "bot": True,
        "vpn": False,
        "vpn_service": "None"
    },
    "metadata": {
        "tor": False,
        "sensor_count": 5,
        "sensor_hits": 100,
        "destination_countries": ["US", "IN"]
    },
    "raw_data": {
        "scan": [
            {"port": 80, "protocol": "TCP"},
            {"port": 443, "protocol": "TCP"}
        ],
        "web_paths": ["/index.html", "/admin"],
        "user_agents": [
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.1722.34",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Safari/537.36 HeyTapBrowser/25.10.8.1.1 Chrome/91.0.4472.88"
        ],
        "ja3": "769,49200-49299"
    },
    "tags": [
        {"name": "Tag1", "category": "Category1", "intention": "Intention1", "description": "Description1"},
        {"name": "Tag2", "category": "Category2", "intention": "Intention2", "description": "Description2"}
    ]
}

# Jinja2 template for HTML table
template = Template("""
<html>
<head>
    <title>Data Table</title>
</head>
<body>
    <h2>Parent Information</h2>
    <table border="1">
        {% for key, value in data.parent.items() %}
        <tr>
            <td>{{ key }}</td>
            <td>{{ value }}</td>
        </tr>
        {% endfor %}
    </table>

    <h2>Metadata</h2>
    <table border="1">
        {% for key, value in data.metadata.items() %}
        <tr>
            <td>{{ key }}</td>
            <td>{{ value }}</td>
        </tr>
        {% endfor %}
    </table>

    <h2>Raw Data</h2>
    <h3>Scan</h3>
    <table border="1">
        <tr>
            <th>Protocol/Port</th>
        </tr>
        {% for item in data.raw_data.scan %}
        <tr>
            <td>{{ item.protocol }}/{{ item.port }}</td>
        </tr>
        {% endfor %}
    </table>

    <h3>Web Paths</h3>
    <table border="1">
        <tr>
            <th>Path</th>
        </tr>
        {% for path in data.raw_data.web_paths %}
        <tr>
            <td>{{ path }}</td>
        </tr>
        {% endfor %}
    </table>

    <h3>User Agents</h3>
    <table border="1">
        <tr>
            <th>User Agent</th>
        </tr>
        {% for agent in data.raw_data.user_agents %}
        <tr>
            <td>{{ agent }}</td>
        </tr>
        {% endfor %}
    </table>

    <h3>JA3</h3>
    <table border="1">
        <tr>
            <td>{{ data.raw_data.ja3 }}</td>
        </tr>
    </table>

    <h2>Tags</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Category</th>
            <th>Intention</th>
            <th>Description</th>
        </tr>
        {% for tag in data.tags %}
        <tr>
            <td>{{ tag.name }}</td>
            <td>{{ tag.category }}</td>
            <td>{{ tag.intention }}</td>
            <td>{{ tag.description }}</td>
        </tr>
        {% endfor %}
    </table>
</body>
</html>
""")

# Render the template with the data
rendered_html = template.render(data=data)

# Write the output to an HTML file
with open("output.html", "w") as f:
    f.write(rendered_html)

print("HTML file generated: output.html")
