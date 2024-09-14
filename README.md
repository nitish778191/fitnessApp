<script type="module">
    document.addEventListener('DOMContentLoaded', () => {
        const searchInput = document.getElementById('searchInput');
        const huntTableBody = document.getElementById('huntTableBody');

        // Assuming huntEntries is available in this scope
        const huntEntries = [
          {
            id: 'TH-0013',
            collection: 'hunts',
            data: {
              title: 'sliver C2 Beacon Execution - MacOS',
              hypothesis: 'Adversaries will attempt the execution of a payload to establish a C2. Sliver is a popular Open Source C2 framework.',
              description: 'Modern C2 frameworks like Cobalt Strike and Sliver allow threat actors and Red Teamers...',
              // ...other data
            }
          },
          // ...other hunt entries
        ];

        // Create rows based on huntEntries when the page loads
        huntEntries.forEach(entry => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${entry.id}</td>
                <td>${entry.data.title}</td>
                <td>${entry.data.hypothesis}</td>
                <td>${entry.data.description}</td>
            `;
            row.setAttribute('data-id', entry.id);
            row.setAttribute('data-title', entry.data.title.toLowerCase());
            row.setAttribute('data-hypothesis', entry.data.hypothesis.toLowerCase());
            row.setAttribute('data-description', entry.data.description.toLowerCase());
            huntTableBody.appendChild(row);
        });

        function filterHunts() {
            const query = searchInput.value.toLowerCase();

            huntTableBody.querySelectorAll('tr').forEach(row => {
                try {
                    const id = row.getAttribute('data-id').toLowerCase();
                    const title = row.getAttribute('data-title').toLowerCase();
                    const hypothesis = row.getAttribute('data-hypothesis').toLowerCase();
                    const description = row.getAttribute('data-description').toLowerCase();

                    // Check if any of the attributes contain the query
                    if (id.includes(query) || title.includes(query) || hypothesis.includes(query) || description.includes(query)) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                } catch (error) {
                    row.style.display = 'none';
                }
            });
        }

        searchInput.addEventListener('input', filterHunts);

        // Initial call to display rows based on the initial state of the input
        filterHunts();
    });
</script>
