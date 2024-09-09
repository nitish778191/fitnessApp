---
import { huntEntries } from "../../huntcontent";
import { techniques } from "../../mitre";
import Layout from "../../layouts/Layout.astro";

const baseURL = import.meta.env.BASE_URL;

---

<Layout title="All Hunts">
    <main class="max-w-screen-xl mx-10 mt-10 2x1:mx-auto">
       
        <h1 class="my-5 text-2xl font-lseg">All Hunts</h1>
        <input
        placeholder="Search using Hunt ID or Title..."
        type="search"
        class="block w-full rounded bg-gray-200 p-4 mb-4"
        x-model="searchQuery"
      />

        <div class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto my-5 text-sm flex flex-row-reverse gap-8 font-sans">
            <a class="rounded-md py-1 px-3 bg-gray-700 text-white" href="hunts.json" download>Download all hunt data (JSON)</a>
        </div>
        <table class="w-full table-auto border-collapse text-md">
            <thead class="text-lseg-meddarkgrey">
                <th scope="col" class="py-2 px-4 text-center">ID</th>
                <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Ticket</th>

                <th scope="col" class="py-2 px-4 text-left">Title</th>
                <th scope="col" class="py-2 px-4 text-left">ATT&CK Techniques</th>
                <th scope="col" class="hidden lg:table-cell py-2 px-4 text-center">Platform</th>
                <th scope="col" class="py-2 px-4 text-center">Creation Date</th>
            </thead>
            <tbody>
                {
                    huntEntries.map((hunt) => (
                        <tr class="text-lseg-darkgrey text-sm">
                            <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap"><a class="py-4 px-4 hover:text-lseg-blue hover:underline" 
			href={hunt.id.toLowerCase()}>{hunt.id}</a></td>
                            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{hunt.data.hunt_ticket}</td>
                            <td class="border-t border-b border-lseg-lightgrey text-left"><a class="block py-4 px-4 hover:text-lseg-blue hover:underline" href={hunt.id.toLowerCase()}>
			{hunt.data.title}</a></td>
                            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left"><div class="flex flex-wrap gap-4">
		{hunt.data.attack_coverage.map(item => [<a title={techniques.find(t => t.id === item.technique)!.name} 
		class="hover:underline hover:text-lseg-blue" href={baseURL + "/technique/" + item.technique}>
		{item.technique}</a>].concat(item.subtechniques?.map(st => <a class="hover:underline hover:text-lseg-blue" title={techniques.find(t => t.id === st)!.name} 
			href={baseURL + "/technique/" + st}>{st}</a>)))}</div></td>
                            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{hunt.data.platform}</td>
                            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{hunt.data.creation_date}</td>
                        </tr>
                    ))
                }

             </tbody>
       </table>
    </main>
   

</Layout>
