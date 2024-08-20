for tag in Tags:
    body_html_table += """
    <tr>
        <td>
            <details>
                <summary>Tags-Name: {}</summary>
            </details>
        </td>
        <td>
            <details>
                <summary>Tags-Category</summary>
                <p>{}</p>
            </details>
        </td>
        <td>
            <details>
                <summary>Tags-Intention</summary>
                <p>{}</p>
            </details>
        </td>
        <td>
            <details>
                <summary>Tag-Description</summary>
                <p>{}</p>
            </details>
        </td>
    </tr>
    """.format(
        str(tag['name']),
        str(tag['category']),
        str(tag['intention']),
        str(tag['description'])
    )
