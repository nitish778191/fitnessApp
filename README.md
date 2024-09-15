<div
  x-data="{
    searchQuery: '',
    huntEntries: [
      {
        id: '1',
        data: {
          title: 'Threat Hunt 1',
          hypothesis: 'Hypothesis 1',
          description: 'Description 1',
          hunt_ticket: 'Ticket 1',
          attack_coverage: [{ technique: 'T1001' }],
          platform: 'Windows',
          creation_date: '2024-09-16'
        }
      },
      {
        id: '2',
        data: {
          title: 'Threat Hunt 2',
          hypothesis: 'Hypothesis 2',
          description: 'Description 2',
          hunt_ticket: 'Ticket 2',
          attack_coverage: [{ technique: 'T1002' }],
          platform: 'Linux',
          creation_date: '2024-09-17'
        }
      }
      // Add more hunt entries as needed
    ],
    get filteredItems() {
      const query = this.searchQuery.toLowerCase();
      return this.huntEntries.filter(entry => {
        return (
          entry.id.toLowerCase().includes(query) ||
          entry.data.title.toLowerCase().includes(query) ||
          entry.data.hypothesis.toLowerCase().includes(query) ||
          entry.data.description.toLowerCase().includes(query)
        );
      });
    }
  }">

  <!-- Search Input -->
  <input
    type="text"
    x-model="searchQuery"
    placeholder="Search by ID, Title, Hypothesis, or Description..."
    class="w-full border-gray-300 rounded-md placeholder-gray-400 text-sm focus:border-orange-500 focus:ring-orange-500"
  />

  <!-- Filtered Table Rows -->
  <table class="w-full table-auto border-collapse text-md mt-6">
    <thead class="text-lseg-meddarkgrey">
      <tr>
        <th scope="col" class="py-2 px-4 text-center">ID</th>
        <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Ticket</th>
        <th scope="col" class="py-2 px-4 text-left">Title</th>
        <th scope="col" class="py-2 px-4 text-left">ATT&CK Techniques</th>
        <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Platform</th>
        <th scope="col" class="py-2 px-4 text-center">Creation Date</th>
      </tr>
    </thead>
    <tbody>
      <!-- Loop through filtered huntEntries -->
      <template x-for="hunt in filteredItems" :key="hunt.id">
        <tr class="text-lseg-darkgrey text-sm">
          <td class="border-t border-b border-lseg-lightgrey text-center">
            <a :href="hunt.id.toLowerCase()" class="py-4 px-4 hover:text-lseg-blue hover:underline">
              <span x-text="hunt.id"></span>
            </a>
          </td>
          <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center" x-text="hunt.data.hunt_ticket"></td>
          <td class="border-t border-b border-lseg-lightgrey text-left">
            <a :href="hunt.id.toLowerCase()" class="block py-4 px-4 hover:text-lseg-blue hover:underline">
              <span x-text="hunt.data.title"></span>
            </a>
          </td>
          <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
            <div class="flex flex-wrap gap-4">
              <template x-for="technique in hunt.data.attack_coverage">
                <a :href="'/technique/' + technique.technique" class="hover:underline hover:text-lseg-blue">
                  <span x-text="technique.technique"></span>
                </a>
              </template>
            </div>
          </td>
          <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center" x-text="hunt.data.platform"></td>





          [[][][][][]
          [][][][][
          [][][][][][
          [][][][][][
          [][][][][][]
        
<script src="searchHuntEntries.js"></script>
  
  <script>
    document.addEventListener('DOMContentLoaded', () => {
      const searchInput = document.getElementById('search-input');
      const tableBody = document.getElementById('huntTableBody');
      
      // Function to filter hunt entries and update the table
      function filterHunts() {
        const searchQuery = searchInput.value.toLowerCase();
        const ids = (window as any).searchHuntEntries(
            (window as any).huntEntries,
            searchQuery,
            'description' // Example field to search
        );

        // Clear the table body
        tableBody.innerHTML = '';

        // Filter and display rows
        (window as any).huntEntries.forEach(entry => {
          if (ids.includes(entry.id)) {
            const row = document.createElement('tr');
            row.className = 'text-lseg-darkgrey text-sm';

            row.innerHTML = `
              <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
                <a class="py-4 px-4 hover:text-lseg-blue hover:underline" href="${entry.id.toLowerCase()}">
                  ${entry.id}
                </a>
              </td>
            `;
            tableBody.appendChild(row);
          }
        });
      }

      // Attach input event listener to the search input
      searchInput.addEventListener('input', filterHunts);
    });
  </script>




  ..//
  import { huntEntries } from './huntEntries.js';

function searchHuntEntries(entries, searchQuery, field) {
  const query = searchQuery.toLowerCase();
  return entries
    .filter(entry => entry.data[field].toLowerCase().includes(query))
    .map(entry => entry.id);
}

// Expose to global scope
window.searchHuntEntries = searchHuntEntries;
window.huntEntries = huntEntries;

