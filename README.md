---
import { huntEntries } from "../../huntcontent";
import { techniques } from "../../mitre";
import Layout from "../../layouts/Layout.astro";

const baseURL = import.meta.env.BASE_URL;
---

<!-- Include Alpine.js -->
<script src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js" defer></script>

<Layout title="All Hunts">
  <main class="max-w-screen-xl mx-10 mt-10 2x1:mx-auto" x-data="searchComponent">
    <h1 class="my-5 text-2x1 font-lseg">All Hunts</h1>

    <!-- Search Input -->
    <input
      placeholder="Search using Hunt ID or Title..."
      type="search"
      class="block w-full rounded bg-gray-200 p-4 mb-4"
      x-model="searchQuery" <!-- Bind input to searchQuery -->
    />

    <table class="w-full table-auto border-collapse text-md">
      <thead class="text-lseg-meddarkgrey">
        <tr>
          <th scope="col" class="py-2 px-4 text-center">ID</th>
          <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Ticket</th>
          <th scope="col" class="py-2 px-4 text-left">ATT&CK Techniques</th>
          <th scope="col" class="py-2 px-4 text-left">Title</th>
          <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Platform</th>
          <th scope="col" class="py-2 px-4 text-center">Creation Date</th>
        </tr>
      </thead>
      <tbody>
        <!-- Loop through filtered hunts -->
        <template x-for="hunt in filteredHunts" :key="hunt.id">
          <tr class="text-lseg-darkgrey text-sm">
            <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
              <a 
                class="py-4 px-4 hover:text-lseg-blue hover:underline"
                :href="`/${hunt.id.toLowerCase()}`"
              >
                <span x-text="hunt.id"></span>
              </a>
            </td>

            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
              <span x-text="hunt.data.hunt_ticket"></span>
            </td>

            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
              <div class="flex flex-wrap gap-4">
                <template x-for="item in hunt.data.attack_coverage" :key="item.technique">
                  <span>
                    <a
                      :title="getTechniqueName(item.technique)"
                      class="hover:underline hover:text-lseg-blue"
                      :href="`${baseURL}/technique/${item.technique}`"
                      x-text="item.technique"
                    ></a>
                    <template x-for="st in item.subtechniques" :key="st">
                      <a
                        class="hover:underline hover:text-lseg-blue"
                        :title="getTechniqueName(st)"
                        :href="`${baseURL}/technique/${st}`"
                        x-text="st"
                      ></a>
                    </template>
                  </span>
                </template>
              </div>
            </td>

            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
              <span x-text="hunt.data.title"></span>
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
  </main>

  <script>
    function searchComponent() {
      return {
        searchQuery: '', // Search input query
        hunts: ${JSON.stringify(huntEntries)}, // Data fetched from server
        get filteredHunts() {
          // Filter hunt entries based on search query for ID or Title
          if (this.searchQuery.trim() === '') {
            return this.hunts;
          }

          const query = this.searchQuery.toLowerCase();
          return this.hunts.filter(hunt =>
            hunt.id.toLowerCase().includes(query) ||  // Search by ID
            hunt.data.title.toLowerCase().includes(query)  // Search by Title
          );
        },
        getTechniqueName(id) {
          // Helper method to get the technique name
          const technique = techniques.find(t => t.id === id);
          return technique ? technique.name : id;
        }
      };
    }
  </script>
</Layout>
