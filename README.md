<script type="module">
    document.addEventListener('DOMContentLoaded', () => {
        const searchInput = document.getElementById('searchInput');
        const huntTableBody = document.getElementById('huntTableBody');

        function filterHunts() {
            const query = searchInput.value.toLowerCase();

            huntTableBody.querySelectorAll('tr').forEach(row => {
                try {
                    const idCell = row.querySelector('td:nth-child(1) a');
                    const titleCell = row.querySelector('td:nth-child(3) a');

                    if (idCell && titleCell) {
                        const id = idCell.textContent.toLowerCase();
                        const title = titleCell.textContent.toLowerCase();

                        if (id.includes(query) || title.includes(query)) {
                            row.style.display = '';
                        } else {
                            row.style.display = 'none';
                        }
                    } else {
                        row.style.display = 'none';
                    }
                } catch (error) {
                    row.style.display = 'none';
                }
            });
        }

        searchInput.addEventListener('input', filterHunts);
        filterHunts();
    });
</script>
