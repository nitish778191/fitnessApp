<script type="module">
  document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('searchInput');
    const huntTableRows = document.querySelectorAll('#huntTableBody tbody tr');

    function filterHunts() {
      const query = searchInput.value.toLowerCase();

      huntTableRows.forEach((row, index) => {
        const id = row.cells[0].textContent.toLowerCase();
        const title = row.cells[1].textContent.toLowerCase();
        const hypothesis = row.cells[2].textContent.toLowerCase();
        const description = row.cells[3].textContent.toLowerCase();

        if (
          id.includes(query) ||
          title.includes(query) ||
          hypothesis.includes(query) ||
          description.includes(query)
        ) {
          row.style.display = ''; // Show row
        } else {
          row.style.display = 'none'; // Hide row
        }
      });
    }

    searchInput.addEventListener('input', filterHunts);
  });
</script>
