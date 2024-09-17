---
import Layout from "../components/Layout"; // Importing Layout component
import { huntEntries } from "../../huntcontent"; // Importing huntEntries data
---

<Layout title="All Hunts">
    <main class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto">
        <h1 class="my-5 text-2xl font-bold">All Hunts</h1>

        <!-- Alpine.js search logic and table -->
        <div x-data="huntEntriesData()">
            <!-- Search Input -->
            <input
                x-ref="searchInput"
                x-model="search"
                x-on:keydown.window.prevent.slash="$refs.searchInput.focus()"
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
                    <template x-for="hunt in filteredHuntEntries" :key="hunt.id">
                        <tr class="text-lseg-darkgrey text-sm">
                            <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap">
                                <a
                                    class="py-4 px-4 hover:text-lseg-blue hover:underline"
                                    :href="hunt.id.toLowerCase()"
                                    x-text="hunt.id"
                                ></a>
                            </td>
                            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">
                                <span x-text="hunt.data.hunt_ticket"></span>
                            </td>
                            <td class="border-t border-b border-lseg-lightgrey text-left">
                                <a
                                    class="block py-4 px-4 hover:text-lseg-blue hover:underline"
                                    :href="hunt.id.toLowerCase()"
                                    x-text="hunt.data.title"
                                ></a>
                            </td>
                            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left">
                                <div class="flex flex-wrap gap-4">
                                    <template x-for="item in hunt.data.attack_coverage">
                                        <a
                                            :href="'/technique/' + item.technique"
                                            class="hover:underline hover:text-lseg-blue"
                                            x-text="item.technique"
                                        ></a>
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

    <!-- Alpine.js script -->
    <script>
        document.addEventListener('alpine:init', () => {
          Alpine.data('huntEntriesData', () => ({
            search: "",
            allHuntEntries: {{ huntEntries | jsonify }},
            get filteredHuntEntries() {
              if (this.search === "") {
                return this.allHuntEntries;
              }
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
</Layout>


<script src="//unpkg.com/alpinejs" defer></script>
