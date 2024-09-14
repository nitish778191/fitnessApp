document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('searchInput');
    const huntTableBody = document.getElementById('huntTableBody');

    // Function to filter hunts based on user input
    function filterHunts() {
        const query = searchInput.value.toLowerCase();
        // Clear the table body
        huntTableBody.innerHTML = '';

        // Check each hunt entry
        huntEntries.forEach(entry => {
            const { id, data } = entry;
            const { title, hypothesis, description } = data;

            // Check if the search query matches any of the fields
            const matches = [id, title, hypothesis, description].some(field =>
                field.toLowerCase().includes(query)
            );

            if (matches) {
                // Create a new row with the hunt entry data
                const row = document.createElement('tr');
                row.className = 'text-lseg-darkgrey text-sm';

                row.innerHTML = `
                    <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
                        <a class="py-4 px-4 hover:text-lseg-blue hover:underline" href="${id.toLowerCase()}">
                            ${id}
                        </a>
                    </td>
                    <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                        ${data.hunt_ticket || 'N/A'}
                    </td>
                    <td class="border-t border-b border-lseg-lightgrey text-left">
                        <a class="block py-4 px-4 hover:text-lseg-blue hover:underline" href="${id.toLowerCase()}">
                            ${title}
                        </a>
                    </td>
                    <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
                        <div class="flex flex-wrap gap-4">
                            ${data.attack_coverage.map(item => `
                                <a title="${item.technique}" class="hover:underline hover:text-lseg-blue" href="${baseURL}/technique/${item.technique}">
                                    ${item.technique}
                                </a>
                            `).join('')}
                        </div>
                    </td>
                    <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                        ${data.platform || 'N/A'}
                    </td>
                    <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                        ${data.creation_date || 'N/A'}
                    </td>
                `;

                huntTableBody.appendChild(row);
            }
        });
    }

    // Event listener to trigger the filter function when user inputs search query
    searchInput.addEventListener('input', filterHunts);

    // Initial call to populate the table with all entries
    filterHunts();
});
