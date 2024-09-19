<script>
  document.addEventListener('alpine:init', () => {
    Alpine.data('huntEntriesData', () => ({
      search: "", // Search term
      allHuntEntries: [], // Initialized with empty data, will be filled later

      init() {
        // Initialize hunt entries data
        try {
          const huntEntriesData = JSON.parse(document.getElementById('huntEntriesData').textContent);
          this.allHuntEntries = huntEntriesData;
        } catch (error) {
          console.error("Failed to load hunt entries data:", error);
        }
      },

      // Computed property for filtered hunt entries
      get filteredHuntEntries() {
        if (this.search === "") {
          return this.allHuntEntries; // Return all entries if no search is applied
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
      }
    }));
  });
</script>
<Layout title="All Hunts">
  <main class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto">
    <h1 class="my-5 text-2xl font-bold">All Hunts</h1>

    <!-- Search Input -->
    <input
      x-model="search"
      x-ref="searchInput"
      x-on:keydown.window.prevent.slash="$refs.searchInput.focus()"
      placeholder="Search for a hunt entry..."
      type="search"
      class="block w-full rounded bg-gray-200 p-4 mb-4"
    />

    <!-- Hunts Table -->
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
      <tbody id="huntTableBody">
        <!-- Use x-for to loop through filtered hunt entries -->
        <template x-for="hunt in filteredHuntEntries" :key="hunt.id">
          <tr class="text-lseg-darkgrey text-sm">
            <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
              <a class="py-4 px-4 hover:text-lseg-blue hover:underline" href="#" x-text="hunt.id"></a>
            </td>
            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center" x-text="hunt.data.hunt_ticket"></td>
            <td class="border-t border-b border-lseg-lightgrey text-left">
              <a class="block py-4 px-4 hover:text-lseg-blue hover:underline" href="#" x-text="hunt.data.title"></a>
            </td>
            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
              <div class="flex flex-wrap gap-4">
                <template x-for="item in hunt.data.attack_coverage">
                  <a :href="`/technique/${item.technique}`" class="hover:underline hover:text-lseg-blue" x-text="item.technique"></a>
                </template>
              </div>
            </td>
            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center" x-text="hunt.data.platform"></td>
            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center" x-text="hunt.data.creation_date"></td>
          </tr>
        </template>
      </tbody>
    </table>
  </main>

  <!-- Add the JSON data -->
  <script type="application/json" id="huntEntriesData">
    {JSON.stringify(huntEntries)}
  </script>
</Layout>
