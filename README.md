<script>
  function searchComponent() {
    return {
      searchQuery: '',
      huntEntries: JSON.parse(document.getElementById('huntEntriesData').textContent),
      get filteredItems() {
        const query = this.searchQuery.toLowerCase();
        return this.huntEntries.filter(entry => {
          const id = entry.id.toLowerCase();
          const title = entry.data.title.toLowerCase();
          const hypothesis = entry.data.hypothesis.toLowerCase();
          const description = entry.data.description.toLowerCase();
          return id.includes(query) || title.includes(query) || hypothesis.includes(query) || description.includes(query);
        });
      }
    };
  }
</script>

        <script id="huntEntriesData" type="application/json">
          {JSON.stringify(huntEntries)}
        </script>



        ---
import BaseLayout from "@/layouts/BaseLayout.astro";
const huntEntries = Astro.props.huntEntries;
---
<BaseLayout>
  <section class="bg-white relative overflow-hidden">
    <div class="w-full mx-auto 2xl:max-w-7xl flex flex-col justify-center py-24 relative p-8">
      <div class="prose text-gray-500 prose-sm prose-headings:font-normal prose-headings:text-xl mx-auto max-w-sm w-full">
        <div>
          <h1>Simple search</h1>
          <p class="text-balance">Search for the words you are looking for.</p>
        </div>
      </div>
      <div class="mt-6 border-t pt-12 max-w-sm mx-auto w-full">
        <!-- Starts component -->

        <!-- Pass huntEntries as JSON to the frontend -->
        <script id="huntEntriesData" type="application/json">
          {JSON.stringify(huntEntries)}
        </script>

        <div x-data="searchComponent()">
          <!-- Search Input -->
          <input
            type="text"
            x-model="searchQuery"
            placeholder="Search..."
            class="w-full border-gray-300 rounded-md placeholder-gray-400 text-sm focus:border-orange-500 focus:ring-orange-500"
          />

          <!-- Table with Filtered Items -->
          <table class="w-full mt-6 border-collapse">
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
              <template x-for="entry in filteredItems" :key="entry.id">
                <tr class="text-lseg-darkgrey text-sm">
                  <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
                    <a class="py-4 px-4 hover:text-lseg-blue hover:underline" :href="entry.id.toLowerCase()">
                      <span x-text="entry.id"></span>
                    </a>
                  </td>
                  <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                    <span x-text="entry.data.hunt_ticket || 'N/A'"></span>
                  </td>
                  <td class="border-t border-b border-lseg-lightgrey text-left">
                    <a class="block py-4 px-4 hover:text-lseg-blue hover:underline" :href="entry.id.toLowerCase()">
                      <span x-text="entry.data.title"></span>
                    </a>
                  </td>
                  <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
                    <div class="flex flex-wrap gap-4">
                      <template x-for="item in entry.data.attack_coverage" :key="item.technique">
                        <a :title="item.technique" class="hover:underline hover:text-lseg-blue" :href="'/technique/' + item.technique">
                          <span x-text="item.technique"></span>
                        </a>
                      </template>
                    </div>
                  </td>
                  <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                    <span x-text="entry.data.platform || 'N/A'"></span>
                  </td>
                  <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                    <span x-text="entry.data.creation_date || 'N/A'"></span>
                  </td>
                </tr>
              </template>
            </tbody>
          </table>
        </div>

        <!-- Ends component -->
      </div>
    </div>
  </section>

  <!-- Add script here if not added in <head> -->
  <script>
    function searchComponent() {
      return {
        searchQuery: '',
        huntEntries: JSON.parse(document.getElementById('huntEntriesData').textContent),
        get filteredItems() {
          const query = this.searchQuery.toLowerCase();
          return this.huntEntries.filter(entry => {
            const id = entry.id.toLowerCase();
            const title = entry.data.title.toLowerCase();
            const hypothesis = entry.data.hypothesis.toLowerCase();
            const description = entry.data.description.toLowerCase();
            return id.includes(query) || title.includes(query) || hypothesis.includes(query) || description.includes(query);
          });
        }
      };
    }
  </script>
</BaseLayout>


