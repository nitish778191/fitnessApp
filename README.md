<!DOCTYPE html>
<html>
<head>
    <title>Data Table</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <h2>Data Table</h2>

    <h3>Parent</h3>
    <table>
        <tr>
            <th>IP</th>
            <th>First Seen</th>
            <th>Last Seen</th>
            <th>Seen</th>
            <th>Actor</th>
            <th>Spoofable</th>
            <th>Classification</th>
            <th>CVE</th>
            <th>Bot</th>
            <th>VPN</th>
            <th>VPN Service</th>
        </tr>
        <tr>
            <td>38.242.209.160</td>
            <td>2024-07-02</td>
            <td>2024-07-09</td>
            <td>...</td>
            <td>unknown</td>
            <td>false</td>
            <td>malicious</td>
            <td>CVE-2018-13379, CVE-2020-2034, CVE-2021-...</td>
            <td>false</td>
            <td>true</td>
            <td>Unknown</td>
        </tr>
    </table>

    <h3>Metadata</h3>
    <table>
        <tr>
            <th>TOR</th>
            <th>Sensor Count</th>
            <th>Sensor Hits</th>
            <th>Destination Countries</th>
        </tr>
        <tr>
            <td>false</td>
            <td>10</td>
            <td>15</td>
            <td>USA, UK, France</td>
        </tr>
    </table>

    <h3>Raw Data</h3>
    <table>
        <tr>
            <th>Scan</th>
            <th>Web Paths</th>
            <th>User-Agents</th>
            <th>JA3</th>
        </tr>
        <tr>
            <td>TCP/443, UDP/53</td>
            <td>/admin, /login</td>
            <td>Mozilla/5.0, Chrome/91.0</td>
            <td>abc123, def456</td>
        </tr>
    </table>

    <h3>Tags</h3>
    <table>
        <tr>
            <th>Name</th>
            <th>Category</th>
            <th>Intention</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>Malware</td>
            <td>Security</td>
            <td>Attack</td>
            <td>Malware infection attempts</td>
        </tr>
        <tr>
            <td>Phishing</td>
            <td>Social Engineering</td>
            <td>Attack</td>
            <td>Phishing attempts targeting credentials</td>
        </tr>
    </table>

</body>
</html>
