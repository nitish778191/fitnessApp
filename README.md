---
import BaseLayout from "@/layouts/BaseLayout.astro";
import { huntEntries } from "../../huntcontent"; // Assuming this contains your tabular data
---

<BaseLayout>
  <section class="bg-white relative overflow-hidden" x-data="{
      searchQuery: '',
      filteredItems() {
        if (this.searchQuery === '') {
          return this.items;
        }
        return this.items.filter(hunt => 
          hunt.id.toLowerCase().includes(this.searchQuery.toLowerCase()) || 
          hunt.data.title.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
      },
      items: huntEntries
    }">
    <div class="w-full mx-auto 2xl:max-w-7xl flex flex-col justify-center py-24 relative p-8">
      <div class="prose text-gray-500 prose-sm prose-headings:font-normal prose-headings:text-xl mx-auto max-w-sm w-full">
        <div>
          <h1>Search Hunts</h1>
          <p class="text-balance">Search by Hunt ID or Title.</p>
        </div>
      </div>

      <!-- Search Input -->
      <div class="mt-6 border-t pt-12 max-w-sm mx-auto w-full">
        <input
          type="text"
          x-model="searchQuery"
          placeholder="Search using Hunt ID or Title..."
          class="w-full border-gray-300 rounded-md placeholder-gray-400 text-sm focus:border-orange-500 focus:ring-orange-500"
        />
      </div>

      <!-- Filtered Table -->
      <div class="mt-6 overflow-x-auto">
        <table class="min-w-full table-auto border-collapse text-md">
          <thead class="text-gray-500">
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
            <template x-for="hunt in filteredItems()" :key="hunt.id">
              <tr class="text-gray-700">
                <td class="border-t border-b py-4 px-4 text-center">
                  <a class="hover:text-orange-500 hover:underline" :href="hunt.id.toLowerCase()">
                    <span x-text="hunt.id"></span>
                  </a>
                </td>
                <td class="hidden lg:table-cell border-t border-b py-4 px-4 text-center" x-text="hunt.data.hunt_ticket"></td>
                <td class="border-t border-b py-4 px-4 text-left">
                  <a class="hover:text-orange-500 hover:underline" :href="hunt.id.toLowerCase()">
                    <span x-text="hunt.data.title"></span>
                  </a>
                </td>
                <td class="border-t border-b py-4 px-4 text-left">
                  <div class="flex flex-wrap gap-4">
                    <template x-for="item in hunt.data.attack_coverage" :key="item.technique">
                      <span>
                        <a class="hover:underline hover:text-orange-500" :href="`/technique/${item.technique}`" x-text="item.technique"></a>
                        <template x-for="st in item.subtechniques" :key="st">
                          <a class="hover:underline hover:text-orange-500" :href="`/technique/${st}`" x-text="st"></a>
                        </template>
                      </span>
                    </template>
                  </div>
                </td>
                <td class="hidden lg:table-cell border-t border-b py-4 px-4 text-center" x-text="hunt.data.platform"></td>
                <td class="border-t border-b py-4 px-4 text-center" x-text="hunt.data.creation_date"></td>
              </tr>
            </template>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</BaseLayout>
