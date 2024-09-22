<Layout title="All Hunts">
  <main class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto">
    <h1 class="my-5 text-2xl font-semibold">All Hunt Entries</h1>

    <!-- Alpine.js Hunt Entries Component -->
    <div x-data="huntEntriesData" x-init="init()">
      <!-- Search Input -->
      <input
        x-model="search"
        placeholder="Search for a hunt entry..."
        type="search"
        class="block w-full rounded bg-gray-200 p-4 mb-4"
      />

      <!-- Hunt Entries Table -->
      <table class="w-full table-auto border-collapse text-md">
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
          <!-- Loop through filtered hunt entries -->
          <template x-for="hunt in filteredHuntEntries" :key="hunt.id">
            <tr class="text-lseg-darkgrey text-sm">
              <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
                <a class="py-4 px-4 hover:text-lseg-blue hover:underline" :href="hunt.id.toLowerCase()">
                  <span x-text="hunt.id"></span>
                </a>
              </td>
              <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                <span x-text="hunt.data.hunt_ticket"></span>
              </td>
              <td class="border-t border-b border-lseg-lightgrey text-left">
                <a class="block py-4 px-4 hover:text-lseg-blue hover:underline" :href="hunt.id.toLowerCase()">
                  <span x-text="hunt.data.title"></span>
                </a>
              </td>
              <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
                <div class="flex flex-wrap gap-4">
                  <template x-for="item in hunt.data.attack_coverage" :key="item.technique">
                    <span>
                      <a :title="techniques.find(t => t.id === item.technique)?.name"
                         class="hover:underline hover:text-lseg-blue"
                         :href="baseURL + '/technique/' + item.technique"
                         x-text="item.technique"></a>
                      <template x-for="st in item.subtechniques" :key="st">
                        <a class="hover:underline hover:text-lseg-blue"
                           :title="techniques.find(t => t.id === st)?.name"
                           :href="baseURL + '/technique/' + st"
                           x-text="st"></a>
                      </template>
                    </span>
                  </template>
                </div>
              </td>
              <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                <span x-text="hunt.data.platform"></span>
              </td>
              <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                <span x-text="hunt.data.creation_date"></span>
              </td>
            </tr>
          </template>
        </tbody>
      </table>
    </div>
  </main>
</Layout>

<!-- Alpine.js Logic -->
<script>
  document.addEventListener('alpine:init', () => {
    Alpine.data('huntEntriesData', () => ({
      search: "", // Search term
      allHuntEntries: [], // All hunt entries data

      // Initialize hunt entries by fetching JSON data
      init() {
        try {
          const huntEntriesData = JSON.parse(document.getElementById('huntEntriesData').textContent);
          this.allHuntEntries = huntEntriesData; // Load the hunt entries data
        } catch (error) {
          console.error("Failed to load hunt entries data:", error);
        }
      },

      // Filter the hunt entries based on the search input
      get filteredHuntEntries() {
        if (this.search === "") {
          return this.allHuntEntries; // Return all entries if no search term is entered
        }

        // Return filtered entries based on the search term
        return this.allHuntEntries.filter((hunt) => {
          return (
            hunt.id.toLowerCase().includes(this.search.toLowerCase()) ||
            hunt.data.title.toLowerCase().includes(this.search.toLowerCase()) ||
            hunt.data.hypothesis.toLowerCase().includes(this.search.toLowerCase()) ||
            hunt.data.description.toLowerCase().includes(this.search.toLowerCase())
          );
        });
      },
    }));
  });
</script>

<!-- JSON Data for Hunt Entries -->
<script type="application/json" id="huntEntriesData">
  {{ huntEntries | jsonify }}
</script>

<div x-data="{
    search: '',
    allHuntEntries: [
        { id: 'TH-001', title: 'Hunt One', description: 'First hunt description' },
        { id: 'TH-002', title: 'Hunt Two', description: 'Second hunt description' },
        // Add more entries as needed
    ],
    get filteredHuntEntries() {
        if (this.search === '') {
            return this.allHuntEntries;
        }
        return this.allHuntEntries.filter(hunt => {
            return (
                hunt.id.toLowerCase().includes(this.search.toLowerCase()) ||
                hunt.title.toLowerCase().includes(this.search.toLowerCase()) ||
                hunt.description.toLowerCase().includes(this.search.toLowerCase())
            );
        });
    }
}">
    <input
        x-model="search"
        placeholder="Search by ID, Title, or Description"
        type="text"
        class="block w-full rounded bg-gray-200 p-4 mb-4"
    />

    <div>
        <template x-for="hunt in filteredHuntEntries" :key="hunt.id">
            <div class="p-4 border-b">
                <h2 x-text="hunt.title"></h2>
                <p x-text="hunt.description"></p>
                <p><strong>ID:</strong> <span x-text="hunt.id"></span></p>
            </div>
        </template>
    </div>
</div>
