<script type="module">
    // Set the global variable `huntEntries` with JSON data from PHP
    window.huntEntries = JSON.parse(`<?php echo $huntEntriesJson; ?>`);
    
    document.addEventListener('DOMContentLoaded', () => {
        const searchInput = document.getElementById('searchInput');
        const huntTableBody = document.getElementById('huntTableBody');

        searchInput.addEventListener('input', () => {
            const query = searchInput.value.toLowerCase();
            huntTableBody.querySelectorAll('tr').forEach(row => {
                const id = row.querySelector('td:nth-child(1) a').textContent.toLowerCase();
                const title = row.querySelector('td:nth-child(3) a').textContent.toLowerCase();
                const huntEntry = window.huntEntries.find(h => h.id.toLowerCase() === id);
                const description = huntEntry?.data.description.toLowerCase() || '';
                const hypothesis = huntEntry?.data.hypothesis.toLowerCase() || '';

                if (id.includes(query) || title.includes(query) || description.includes(query) || hypothesis.includes(query)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    });
</script>
