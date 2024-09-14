<script type="module">
  document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('searchInput');
    const huntTableBody = document.querySelector('#huntTableBody tbody');

    // Function to filter and display hunt entries
    function filterHunts() {
      const query = searchInput.value.toLowerCase();
      huntTableBody.innerHTML = ''; // Clear table

      // Access huntEntries directly in the script
      huntEntries.forEach((entry) => {
        const { id, data: { title, hypothesis, description } } = entry;

        // Check if query matches any field
        if (
          id.toLowerCase().includes(query) ||
          title.toLowerCase().includes(query) ||
          hypothesis.toLowerCase().includes(query) ||
          description.toLowerCase().includes(query)
        ) {
          // Create new table row for matching entries
          const row = document.createElement('tr');
          row.innerHTML = `
            <td>${id}</td>
            <td>${title}</td>
            <td>${hypothesis}</td>
            <td>${description}</td>
          `;
          huntTableBody.appendChild(row);
        }
      });
    }

    // Add event listener to search input
    searchInput.addEventListener('input', filterHunts);

    // Initial population of table
    filterHunts();
  });
</script>
