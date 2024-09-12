<script type="module">
    document.addEventListener('DOMContentLoaded', () => {
        const searchInput = document.getElementById('searchInput');
        const huntTableBody = document.getElementById('huntTableBody');
        const huntEntries = JSON.parse(`{JSON.stringify(huntEntries)}`);

        searchInput.addEventListener('input', () => {
            const query = searchInput.value.toLowerCase();
            huntTableBody.querySelectorAll('tr').forEach(row => {
                const id = row.querySelector('td:nth-child(1) a').textContent.toLowerCase();
                const title = row.querySelector('td:nth-child(3) a').textContent.toLowerCase();
                const description = huntEntries.find(h => h.id.toLowerCase() === id)?.data.description.toLowerCase() || '';
                const hypothesis = huntEntries.find(h => h.id.toLowerCase() === id)?.data.hypothesis.toLowerCase() || '';

                if (id.includes(query) || title.includes(query) || description.includes(query) || hypothesis.includes(query)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    });
</script>
